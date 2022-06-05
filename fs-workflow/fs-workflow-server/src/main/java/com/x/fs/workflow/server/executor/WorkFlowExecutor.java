package com.x.fs.workflow.server.executor;

import com.x.fs.base.dto.ContextDTO;
import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.workflow.server.dto.WorkFlowTrackerDto;
import com.x.fs.workflow.server.inter.IDoWork;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author x
 */
@Slf4j
public class WorkFlowExecutor {

    private DoWorkThreadHelper doWorkThreadHelper;

    private Future executor;

    private ExecutorService executorService;

    private ContextDTO contextDTO;

    private WorkFlowTrackerDto workFlowTrackerDto;

    private class WorkFlowRunner implements Callable<Integer> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Integer call() {
            do {
                WorkFlowLoggerFactory workFlowLoggerFactory;

                try {
                    workFlowLoggerFactory = FsApplicationContext.getBean(WorkFlowLoggerFactory.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("未获取到工作流程日志bean:WorkFlowLoggerFactory。 {}", e);
                    break;
                }
                WorkFlowLogger workFlowLogger = workFlowLoggerFactory.CreateWorkFlowLogger(workFlowTrackerDto.getWfGuid());
                doWorkThreadHelper.setWuLogger(workFlowLogger);
                doWorkThreadHelper.setContext(contextDTO);

                IDoWork doWork;
                try {
                    doWork = FsApplicationContext.getBean(workFlowTrackerDto.getWfFunction());
                } catch (Exception e) {
                    doWorkThreadHelper.getWorkFlowLogger().setFirstResult("-1", "获取对应功能号失败！[" + workFlowTrackerDto.getWfFunction() + "]" + e.getMessage());
                    break;
                }

                try {
                    doWork.doWork(workFlowTrackerDto.getWfRequestText(), doWorkThreadHelper);
                } catch (Throwable e) {
                    e.printStackTrace();
                    // 不应该干涉业务的异常。不写业务失败，以免造成记录重复!
                    log.error("执行工作单元出现异常.[{}]", e.getMessage());
                } finally {
//                    ContextUtils.clearContext();
                }
            } while (false);
            doWorkThreadHelper.release();
            return 0;
        }

    }


    public WorkFlowExecutor(WorkFlowTrackerDto workFlowTrackerDto) {
        this.workFlowTrackerDto = workFlowTrackerDto;
        this.doWorkThreadHelper = new DoWorkThreadHelper();
    }

    public boolean start() {
        try {
            this.setContext();
            WorkFlowRunner workRunner = new WorkFlowRunner();
            this.executorService = Executors.newSingleThreadExecutor();
            this.executor = executorService.submit(workRunner);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isDone() {
        if (this.executor == null) {
            return true;
        }
        try {
            if (this.executor.isDone()) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("check isDone exception. {}", e.getMessage());
        }
        return true;
    }

    public boolean cancel() {
        if (this.executor == null) {
            return true;
        }
        try {
            this.doWorkThreadHelper.setExitLoop();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void setWuLeaseCount(int wuLeaseCount) {
        this.doWorkThreadHelper.setWuLeaseCount(wuLeaseCount);
    }

    /**
     * 获取主线程上下文设置到本地变量
     */
    private void setContext() {
        try {
            // 本地线程功能暂未实现，待后续实现
        } catch (Exception e) {
            log.error("ContextUtils.getContext() exception. errMsg:" + e.getMessage());
        }
    }


}
