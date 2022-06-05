package com.x.fs.workflow.server.service;

import com.x.fs.base.transaction.FsTransactionManager;
import com.x.fs.base.utils.DateUtils;
import com.x.fs.base.utils.StringUtils;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.common.utils.UniqueId;
import com.x.fs.mbg.model.FsWorkFlowTracker;
import com.x.fs.workflow.api.dto.WorkFlowRunnerExtParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerResult;
import com.x.fs.workflow.server.atom.FsWorkFlowTrackerAtom;
import com.x.fs.workflow.server.constant.WorkFlowConstant;
import com.x.fs.workflow.server.dto.*;
import com.x.fs.workflow.server.executor.WorkFlowExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author x
 */
@Order(value = 1)
@Component
@Slf4j
public class WorkFlowTrackerServiceImpl implements DisposableBean, Runnable, CommandLineRunner {

    @Autowired
    private FsWorkFlowTrackerAtom fsWorkFlowTrackerAtom;

    private AtomicBoolean startCheckFlag;
    private boolean exit;

    private static WorkFlowTrackerServiceImpl owner = null;
    private Thread thread;
    private int checkIntervalTime = 3000;
    private int wfLeaseTime = 10;
    private int wfGraceTime = 30;
    private LocalTime checkBeginTime;
    private LocalTime checkEndTime;
    private AtomicBoolean backgroundSvrInitFlag;

    private Map<String, TrackerGroup> trackerGroupMap = new HashMap<>();

    private Map<String, Task> tasks;
    private Lock lock = new ReentrantLock();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        log.info("WorkFlowTrackerService run thread...");
        int originCheckIntervalTime = this.checkIntervalTime;
        boolean alreadyLoadConfig = false;
        while (!this.exit) {
            try {
                Thread.sleep(originCheckIntervalTime);
            } catch (InterruptedException e) {
                log.error("work flow tracker call sleep exception：[{}]. stackTrace:[{}]", e.getMessage(), e.getStackTrace());
            }
            if (!this.startCheckFlag.get()) {
                continue;
            }
            if (!alreadyLoadConfig) {
                this.LoadConfig();
                alreadyLoadConfig = true;
                log.info("工作流程调度检查服务启动成功！");
            }
            boolean needAdjustCheckTime = false;
            try {
                needAdjustCheckTime = this.keepAlive();
                if (!needAdjustCheckTime && originCheckIntervalTime != this.checkIntervalTime) {
                    // 当无任务运行时，复原回检查间隔时间长
                    originCheckIntervalTime = this.checkIntervalTime;
                }
            } catch (Exception e) {
                log.error("work flow tracker thread do keepAlive exception: [{}] stackTrace:[{}]", e.getMessage(), e.getStackTrace());
            }
            try {
                LocalTime time = LocalTime.now();
                // 1、发起任务调度入口已增加工作调度检查时间，超过工作调度检查时间时不再增加新的工作
                // 2、对于已经在运行的工作调度任务，无论当前是否处理工作调度检查时间范围内都需要进行检查，直到所有工作任务执行完成
                if (needAdjustCheckTime || (time.compareTo(this.checkBeginTime) > 0 && time.compareTo(this.checkEndTime) <= 0)) {

                    this.checkWorkFlow();

                    if (needAdjustCheckTime) {
                        originCheckIntervalTime = 500;
                    }
                }
            } catch (Exception e) {
                log.error("work flow tracker thread do checkWorkFlow exception: [{}]. stackTrace:[{}]", e.getMessage(), e.getStackTrace());
            }
        }
        log.info("work flow tracker is exit.");
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() {
        this.exit = false;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) {
        startCheckFlag.set(true);
        log.info("工作流程的调度任务检查的服务开始启动。");
    }


    public class Task {
        public String taskName;
        public WorkFlowExecutor workFlowExecutor;
        //如果发起强制关闭，需记下来，等下一轮检查时进行检查是否停止掉了。
        public boolean isCanceFlag;
        WorkFlowTrackerDto workFlowTrackerDto;

        Task(String taskName, WorkFlowExecutor workFlowExecutor, WorkFlowTrackerDto workFlowTrackerDto) {
            this.taskName = taskName;
            this.workFlowExecutor = workFlowExecutor;
            this.workFlowTrackerDto = workFlowTrackerDto;
            this.isCanceFlag = false;
        }
    }

    private class TrackerGroup {
        WorkFlowTrackerOneDto nowData;
        WorkFlowTrackerOneDto prevData;
        List<String> subTrackers;
        boolean remove;
        boolean alreadyNeedToFinish = false;

        TrackerGroup(WorkFlowTrackerOneDto inputParam) {
            this.prevData = inputParam;
            this.nowData = inputParam;
            this.subTrackers = new ArrayList<>();
            if (this.prevData.getWfLeaseCount() == -1) {
                // 第一次加载起来时，已经就是设置为需要强退的任务
                alreadyNeedToFinish = true;
            }
            remove = false;
        }

        void reset(WorkFlowTrackerOneDto inputParam) {
            if (inputParam.getWfLeaseCount() < this.prevData.getWfLeaseCount()) {
                // 这时说明没有再续租了。这时不再更新本地数据
                if (this.prevData.getWfLeaseCount() > this.nowData.getWfLeaseCount()) {
                    this.remove = true;
                }
            } else if (this.alreadyNeedToFinish) {
                // 如果一启时，加载起来的任务已经就是设置为需要强退的任务且经过3个周期仍然没有更新，则不再进行检查
                if (inputParam.getExpiryTime() > inputParam.getWfGraceTime() * 3) {
                    this.remove = true;
                }
            } else {
                this.prevData = this.nowData;
            }
            this.nowData = inputParam;
        }

        boolean isNeedRemove() {
            return remove;
        }
    }


    @PostConstruct
    public void startService() {
        owner = this;
        this.tasks = new HashMap<>();
        this.startCheckFlag = new AtomicBoolean();
        this.startCheckFlag.set(false);
        this.backgroundSvrInitFlag = new AtomicBoolean();
        this.backgroundSvrInitFlag.set(false);
        startBackgroundWatcher();
    }

    private void startBackgroundWatcher() {
        log.info("WorkFlowTracker run thread start!");
        this.exit = false;
        this.thread = new Thread(this);
        this.thread.setUncaughtExceptionHandler((t, e) -> {
            this.backgroundSvrInitFlag.set(false);
            log.error("WorkFlowTracker run thread exception![{}]，stackTrace:[{}]", e.getMessage(), e.getStackTrace());
            startBackgroundWatcher();
        });
        this.thread.start();
    }

    private void LoadConfig() {
        //获取工作单元周期检查间隔时长配置出错。系统将采用默认[WF_CHECK_INTERVAL_TIME＝3s]配置进行处理， 可设置为配置变量
        int tmpIntervalTime = 3;
        //获取工作流程租约配置出错。系统将采用默认配置[WF_LEASE_TIME=10s]进行处理
        //已秒为位单位
        this.wfLeaseTime = 10;
        //获取工作流程宽限期配置出错。系统将采用默认配置[WU_GRACE_TIME=30s]进行处理, 可设置为配置变量
        this.wfGraceTime = 30;
        String[] checkTime = new String[2];
        try {
            String value = "00:00:00-23:59:59";
            checkTime = value.split("-");
        } catch (Exception e) {
            log.error("获取工作流程工作时间范围配置出错。系统将采用默认[WF_CHECK_TIME＝09:00:00-23:59:59]配置进行处理。错误信息:" + e.getMessage());
        }
        this.wfLeaseTime = Math.max(this.wfLeaseTime, 10);
        this.wfGraceTime = Math.max(this.wfGraceTime, 30);
        this.checkIntervalTime = tmpIntervalTime * 1000;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            this.checkBeginTime = LocalTime.parse(checkTime[0], formatter);
            this.checkEndTime = LocalTime.parse(checkTime[1], formatter);
        } catch (Exception e) {
            log.error("解析工作流程工作时间范围配置出错。系统将采用默认[WU_CHECK_TIME＝09:00:00-23:59:59]配置进行处理。错误信息:" + e.getMessage());
            this.checkBeginTime = LocalTime.parse("09:00:00", formatter);
            this.checkEndTime = LocalTime.parse("23:59:59", formatter);
        }
        this.backgroundSvrInitFlag.set(true);
    }

    public static WorkFlowTrackerServiceImpl getWorkFlowTracker() {
        return owner;
    }


    private boolean keepAlive() {
        boolean adjustCheckTime = false;
        Map<String, Task> tmp = new HashMap<>();
        try {
            this.lock.lock();
            if (this.tasks.size() > 0) {
                tmp.putAll(this.tasks);
            }
        } finally {
            this.lock.unlock();
        }
        if (tmp.size() <= 0) {
            return false;
        }
        int doneCount = 0;
        try {
            for (Task item : tmp.values()) {
                boolean isDone = item.workFlowExecutor.isDone();
                String wuStatus = isDone ? (item.isCanceFlag ? WorkFlowConstant.WF_STATUS_ABORT_FINISH : WorkFlowConstant.WF_STATUS_NORMAL_FINISH) : WorkFlowConstant.WF_STATUS_RUNNING;
                try {
                    updateWfStatus(item.workFlowTrackerDto.getWfGuid(), wuStatus);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                if (!isDone) {
                    continue;
                }
                try {
                    this.lock.lock();
                    this.tasks.remove(item.workFlowTrackerDto.getWfGuid());
                    this.trackerGroupMap.remove(item.workFlowTrackerDto.getWfGuid());
                    ++doneCount;
                } finally {
                    this.lock.unlock();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("keepAlive失败");
        }
        if (tmp.size() - doneCount > 0) {
            // 需调整下扫描间隔时长
            adjustCheckTime = true;
        }
        return adjustCheckTime;
    }

    private void updateWfStatus(String wfGuid, String wfStatus) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            log.error("updateWfStatus时，检查到当前线程事务处于开启状态");
        }
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager()) {
            int count = fsWorkFlowTrackerAtom.updateWfStatusByUid(wfGuid, wfStatus);
            if (count > 0) {
                fsTransactionManager.doSuccess();
            }
        } catch (Exception e) {
            log.error("updateWfStatus  error:[{}],callStackTrace:[{}]", e.getMessage(), e.getStackTrace());
            throw new FsServiceException(e.getMessage());
        }
    }

    private void updateSignal(String wufuid, Integer wfSignal, Integer wfLeaseCount) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            log.error("updateSignal时，检查到当前线程事务处于开启状态");
        }
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager()) {
            int count = fsWorkFlowTrackerAtom.updateWfSignalByid(wufuid, wfSignal, wfLeaseCount);
            if (count > 0) {
                fsTransactionManager.doSuccess();
            }
        } catch (Exception e) {
            log.error("updateSignal error:[{}],callStackTrace:[{}]", e.getMessage(), e.getStackTrace());
            throw new FsServiceException("updateSignal error:{}" + e.getMessage());
        }
    }


    private void checkWorkFlow() {
        List<WorkFlowTrackerOneDto> unWorkFlow;
        try {
            unWorkFlow = fsWorkFlowTrackerAtom.selectUnFinishWorkFlow();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //同个父单元的工作组进行归组
        setTrackerGroup(unWorkFlow);
        Map<String, Task> tmp = new HashMap<>();
        try {
            this.lock.lock();
            if (this.tasks.size() > 0) {
                tmp.putAll(this.tasks);
            }
        } finally {
            this.lock.unlock();
        }
        for (WorkFlowTrackerOneDto item : unWorkFlow) {
            Task localTask = tmp.get(item.getWfGuid());
            checkServiceWorkFlow(localTask, item);
        }
        // 清理本地缓存tracker数据
        clearTrackerLocalCache(tmp);
        // 检查仅本服务运行的task,防止给别的服务强制停止了而本服务却不知道发生了此事仍在继续运行
        checkLocalTask(tmp, unWorkFlow);
    }

    /**
     * 检查本服务中每项工作流程是否有接收到终止信号
     *
     * @param localTask
     * @param item
     */
    private void checkServiceWorkFlow(Task localTask, WorkFlowTrackerOneDto item) {
        if (localTask == null) {
            // 非本服务运行任务，不处理
            return;
        }
        if (!item.getWfSignal().equals(WorkFlowConstant.WF_SIGNAL_FORCE_STOP)) {
            // 没有强制停止信号的任务，让它一直执行下去,等待它执行完后自动退出
            return;
        }
        localTask.workFlowExecutor.setWuLeaseCount(item.getWfLeaseCount());
        if (localTask.workFlowExecutor.isDone()) {
            // 已完成任务，不需要做其它处理
            return;
        }
        // 检查是否有子任务，如果有则需要先等待子任务退出后，才能发起本任务强退处理
        TrackerGroup tg = trackerGroupMap.get(item.getWfGuid());
        if (tg == null) {
            return;
        }
        if (tg.subTrackers.size() > 0) {
            return;
        }
        if (localTask.workFlowExecutor.isDone()) {
            // 再次检查，如果是已完成任务，不需要做其它处理
            return;
        }
        // 收到外部通知退出，设置任务退出信号
        localTask.isCanceFlag = true;
        localTask.workFlowExecutor.cancel();
    }


    private void setTrackerGroup(List<WorkFlowTrackerOneDto> unWorkFlow) {
        if (unWorkFlow.size() <= 0) {
            // 没有任何未完成任务，需清理本地tracker缓存
            this.trackerGroupMap.clear();
            return;
        }
        for (WorkFlowTrackerOneDto item : unWorkFlow) {
            // 每个工作流程都加入组里
            TrackerGroup it = this.trackerGroupMap.get(item.getWfGuid());
            if (it == null) {
                this.trackerGroupMap.put(item.getWfGuid(), new TrackerGroup(item));
            } else {
                it.reset(item);
            }
            // 检查是否存在父节点，如果存在，则需加入到父节点中。
            if (item.getWfParentGuid().trim().length() <= 0) {
                continue;
            }
            // 如果设置当前工作编号与父工作编号一样，也不重复加入组
            if (item.getWfGuid().equals(item.getWfParentGuid())) {
                continue;
            }
            TrackerGroup pTmp = this.trackerGroupMap.get(item.getWfParentGuid());
            if (pTmp != null) {
                //把子节点WU_GUID添加进来。
                pTmp.subTrackers.add(item.getWfGuid());
                //同时检查是否需要设置子任务中终止信号
                if (pTmp.nowData.getWfSignal().equals(WorkFlowConstant.WF_SIGNAL_FORCE_STOP)) {
                    item.setWfSignal(WorkFlowConstant.WF_SIGNAL_FORCE_STOP);
                }
                continue;
            }
            // 本地缓存中未找到父节点信息时，遍历tracker数据进行建父节点信息。
            for (WorkFlowTrackerOneDto pItem : unWorkFlow) {
                if (pItem.getWfGuid().equals(item.getWfParentGuid())) {
                    TrackerGroup subItem = new TrackerGroup(pItem);
                    subItem.subTrackers.add(item.getWfGuid());
                    this.trackerGroupMap.put(pItem.getWfGuid(), subItem);
                    // 同时检查是否需要设置子任务中终止信号
                    if (pItem.getWfSignal().equals(WorkFlowConstant.WF_SIGNAL_FORCE_STOP)) {
                        item.setWfSignal(WorkFlowConstant.WF_SIGNAL_FORCE_STOP);
                    }
                    break;
                }
            }
        }
    }

    private void clearTrackerLocalCache(Map<String, Task> localTasks) {
        if (this.trackerGroupMap.size() <= 0) {
            return;
        }
        List<String> keys = new ArrayList<>(this.trackerGroupMap.keySet());
        for (String key : keys) {
            TrackerGroup tg = this.trackerGroupMap.get(key);
            if (tg == null) {
                continue;
            }
            tg.subTrackers.clear();
            Task localTaskItem = localTasks.get(key);
            if (localTaskItem != null) {
                continue;
            }
            // 检查不属于自己启动的工作是否已过期
            if (tg.nowData.getExpiryTime() < tg.nowData.getWfGraceTime()) {
                continue;
            }
            // 是过期工作,如果是自己开启的工作，但是由于重启等原因丢失掉了，这时需要把它清理掉。
            // 检查本地数据中preData记录的续租计数是否为-1了，如果是则进行清除本地记录
            if (tg.isNeedRemove()) {
                updateWfStatus(key, WorkFlowConstant.WF_STATUS_ABORT_FINISH);
                this.trackerGroupMap.remove(key);
            } else {
                // 先设置收回续租次数，设置为－1,同时发请终止信号，下一轮检查，如果仍然还在则设置强制退出标识
                updateSignal(tg.nowData.getWfGuid(), WorkFlowConstant.WF_SIGNAL_FORCE_STOP, -1);
            }
        }
    }

    private void checkLocalTask(Map<String, Task> localTasks, List<WorkFlowTrackerOneDto> trackers) {
        for (WorkFlowTrackerOneDto item : trackers) {
            Task task = localTasks.get(item.getWfGuid());
            if (task == null) {
                continue;
            }
            localTasks.remove(item.getWfGuid());
        }
        if (localTasks.size() <= 0) {
            return;
        }
        StringJoiner sj = new StringJoiner("','");
        localTasks.keySet().forEach(sj::add);
        List<WorkFlowTrackerTwoDto> wFTDtoList;
        try {
            wFTDtoList = fsWorkFlowTrackerAtom.selectWfStatusByWfGuides(sj.toString());
        } catch (Exception e) {
            return;
        }
        if (CollectionUtils.isEmpty(wFTDtoList)) {
            return;
        }
        for (WorkFlowTrackerTwoDto item : wFTDtoList) {
            Task localTask = localTasks.get(item.getWfGuid());
            if (localTask == null) {
                continue;
            }
            // 查看下当前库里的任务中信号设置是什么，完成状态是什么，如果信号是退出或完成状态为已完成的则需要停止本微服务运行的任务
            if (item.getWfStatus().equals(WorkFlowConstant.WF_STATUS_ABORT_FINISH)) {
                localTask.isCanceFlag = true;
                localTask.workFlowExecutor.cancel();
                continue;
            }
            if (item.getExpiryTime() > localTask.workFlowTrackerDto.getWfGraceTime() &&
                    item.getWfSignal().equals(WorkFlowConstant.WF_SIGNAL_FORCE_STOP)) {
                localTask.isCanceFlag = true;
                localTask.workFlowExecutor.cancel();
            }
        }
    }


    private String formatTime(LocalTime tm) {
        StringBuilder buf = new StringBuilder(18);
        int hourValue = tm.getHour();
        int minuteValue = tm.getMinute();
        int secondValue = tm.getSecond();
        int nanoValue = tm.getNano();
        buf.append(hourValue < 10 ? "0" : "").append(hourValue)
                .append(minuteValue < 10 ? ":0" : ":").append(minuteValue);
        if (secondValue <= 0) {
            buf.append(":00");
        } else {
            buf.append(secondValue < 10 ? ":0" : ":").append(secondValue);
            if (nanoValue > 0) {
                buf.append('.');
                if (nanoValue % 1000_000 == 0) {
                    buf.append(Integer.toString((nanoValue / 1000_000) + 1000).substring(1));
                } else if (nanoValue % 1000 == 0) {
                    buf.append(Integer.toString((nanoValue / 1000) + 1000_000).substring(1));
                } else {
                    buf.append(Integer.toString((nanoValue) + 1000_000_000).substring(1));
                }
            }
        }
        return buf.toString();
    }


    public WorkFlowTrackerDto allocWorkFlow(WorkFlowRunnerParam inputParam) {
        WorkFlowTrackerDto workFlowTrackerDto = new WorkFlowTrackerDto();
        workFlowTrackerDto.setWfStatus(WorkFlowConstant.WF_STATUS_BEGIN);
        workFlowTrackerDto.setWfGuid(UniqueId.getInstance().nextId().toString());
        workFlowTrackerDto.setWfRequestText(inputParam.getWfRequestText());
        Integer paramWuGraceTime = inputParam.getWfGraceTime() == null ? this.wfGraceTime : inputParam.getWfGraceTime();
        workFlowTrackerDto.setWfGraceTime((long) paramWuGraceTime);
        workFlowTrackerDto.setWfLeaseCount(0);
        workFlowTrackerDto.setWfSignal(0);
        String wuParentGuid = inputParam.getWfParentGuid();
        workFlowTrackerDto.setWfParentGuid(wuParentGuid == null || wuParentGuid.isEmpty() ? " " : wuParentGuid);
        workFlowTrackerDto.setWfLeaseTime(this.wfLeaseTime);
        workFlowTrackerDto.setWfFunction(inputParam.getWfFunction());
        workFlowTrackerDto.setWfName(StringUtils.subStringByByte(inputParam.getWfName(), 64));
        return workFlowTrackerDto;
    }

    public WorkFlowRunnerResult runWorkFlow(WorkFlowTrackerDto inputParam) {
        // 1、必须先检查背景线程是否已经完成起来
        if (!this.backgroundSvrInitFlag.get()) {
            log.error("工作单元调度服务未启动！接受到调度请求:[{}]", inputParam.getWfName());
            throw new FsServiceException("工作调度服务未完全启动");
        }
        // 2、判断是否在工作服务时间
        LocalTime time = LocalTime.now();
        if (time.compareTo(this.checkBeginTime) <= 0 || time.compareTo(this.checkEndTime) > 0) {
            log.error("工作单元调度服务当前处于非工作调度服务时间内！接受到调度请求:[{}],begin_time:[{}],end_time:[{}],current_time:[{}]", inputParam.getWfName(),
                    formatTime(this.checkBeginTime), formatTime(this.checkEndTime), time);
            throw new FsServiceException("当前处于非工作调度服务时间内。[begin_time:" + formatTime(this.checkBeginTime) + ", end_time:" + formatTime(this.checkEndTime) + ",current_time:" + time + "]");
        }
        return runWorkFlowExecutor(inputParam);
    }

    public WorkFlowRunnerResult runRemoteWorkFlow(WorkFlowRunnerExtParam inputParam) {
        WorkFlowTrackerDto workFlowTrackerDto;
        try {
            workFlowTrackerDto = new WorkFlowTrackerDto();
            workFlowTrackerDto.setWfName(inputParam.getWfName());
            workFlowTrackerDto.setWfStatus(WorkFlowConstant.WF_STATUS_BEGIN);
            workFlowTrackerDto.setWfGuid(inputParam.getWfGuid().trim());
            workFlowTrackerDto.setWfRequestText(inputParam.getWfRequestText());
            workFlowTrackerDto.setWfGraceTime(this.wfGraceTime);
            workFlowTrackerDto.setWfLeaseCount(0);
            workFlowTrackerDto.setWfSignal(0);
            String wuParentGuid = inputParam.getWfParentGuid();
            workFlowTrackerDto.setWfParentGuid(wuParentGuid == null || wuParentGuid.isEmpty() ? " " : wuParentGuid);
            workFlowTrackerDto.setWfLeaseTime(this.wfLeaseTime);
            workFlowTrackerDto.setWfFunction(inputParam.getWfFunction());
            workFlowTrackerDto.setWfName(inputParam.getWfName());
        } catch (Exception e) {
            throw new FsServiceException(e.getMessage());
        }
        return runWorkFlowExecutor(workFlowTrackerDto);
    }

    private WorkFlowRunnerResult runWorkFlowExecutor(WorkFlowTrackerDto inputParam) {
        try {
            FsWorkFlowTracker fsWorkFlowTracker = new FsWorkFlowTracker();
            BeanUtils.copyProperties(inputParam, fsWorkFlowTracker);
            fsWorkFlowTracker.setWfFinishTime(DateUtils.getDefualtTime());
            int count = fsWorkFlowTrackerAtom.saveWorkFlowTracker(fsWorkFlowTracker);
            if (count < 1) {
                return new WorkFlowRunnerResult(-1, null);
            }
            WorkFlowExecutor executor = new WorkFlowExecutor(inputParam);
            executor.start();
            try {
                this.lock.lock();
                this.tasks.put(inputParam.getWfGuid(), new Task(inputParam.getWfGuid(), executor, inputParam));
            } finally {
                this.lock.unlock();
            }
        } catch (Exception e) {
            throw new FsServiceException(e.getMessage());
        }
        return new WorkFlowRunnerResult(0, inputParam.getWfGuid());
    }


}
