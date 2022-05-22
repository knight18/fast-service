package com.x.fs.workflow.server.service;

import com.x.fs.common.exception.FsServiceException;
import com.x.fs.mbg.model.FsWorkFlowLog;
import com.x.fs.mbg.model.FsWorkFlowTracker;
import com.x.fs.workflow.api.dto.WorkFlowLogScheduleResult;
import com.x.fs.workflow.api.dto.WorkFlowSignalResult;
import com.x.fs.workflow.server.atom.FsWorkFlowLogAtom;
import com.x.fs.workflow.server.atom.FsWorkFlowTrackerAtom;
import com.x.fs.workflow.server.constant.WorkFlowConstant;
import com.x.fs.workflow.server.utils.WfLogTextInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author x
 */
@Service("workFlowSchedulerClientService")
public class WorkFlowSchedulerClientService {

    @Autowired
    private FsWorkFlowTrackerAtom fsWorkFlowTrackerAtom;

    @Autowired
    private FsWorkFlowLogAtom fsWorkFlowLogAtom;


    public WorkFlowSignalResult signal(String wfGuid, Integer signal) {
        if (!WorkFlowConstant.WF_SIGNAL_FORCE_STOP.equals(signal) && !WorkFlowConstant.WF_SIGNAL_NORMAL.equals(signal)) {
            throw new FsServiceException("入参字段值无效:{" + "WF_SIGNAL}:[" + signal + "]");
        }
        int count = fsWorkFlowTrackerAtom.updateSignalByUid(signal, null, wfGuid);
        WorkFlowSignalResult result = new WorkFlowSignalResult();
        if (count < 1) {
            result.setRetCode(-1);
        } else {
            result.setRetCode(0);
        }
        return result;
    }

    /**
     * 查询工作流程日志。则返回所有记录
     *
     * @param wfGuid
     * @return 工作单元日志
     */
    public List<FsWorkFlowLog> selectWorkFlowLogByUid(String wfGuid) {
        return fsWorkFlowLogAtom.selectWorkFlowLogByUid(wfGuid);
    }

    /**
     * 查询工作流程信息
     *
     * @param wfGuid
     * @return
     */
    public List<FsWorkFlowTracker> selectWorkFlowTrackerByUid(String wfGuid) {
        return fsWorkFlowTrackerAtom.selectWorkFlowTrackerByUid(wfGuid, null);
    }


    /**
     * 查询工作流程日志。如果wfLogId为null，则返回当前最后一笔进度日志。有传值时按 工作单元日志序号 >= wfLogId 返回记录
     *
     * @param wfGuid
     * @return 工作单元日志
     */
    public List<WorkFlowLogScheduleResult> readWorkUnitLogSchedule(String wfGuid, int wfLogId) {
        // 首先判断当前工作单元是否已结束，再查进度日志流水

        List<FsWorkFlowTracker> trackerList = selectWorkFlowTrackerByUid(wfGuid);
        if (CollectionUtils.isEmpty(trackerList)) {
            throw new FsServiceException("工作流程的UID不存在！" + wfGuid);
        }
        FsWorkFlowTracker tacker = trackerList.get(0);
        // 进度按对应格式解析出来再返回
        List<FsWorkFlowLog> workFlowLogs = selectWorkFlowLogByUid(wfGuid);
        List<WorkFlowLogScheduleResult> rt = new ArrayList<>();
        long wuLogSnMax = 0L;
        int count = 0;
        String taskStatus;
        String errCode = "0";
        String errMsg = "运行中";

        for (FsWorkFlowLog item : workFlowLogs) {
            count++;
            WfLogTextInfo log = new WfLogTextInfo(item.getWfLogText());
            if (!log.open().equals(0)) {
                continue;
            }
            wuLogSnMax = (wuLogSnMax > item.getWfLogId() ? wuLogSnMax : item.getWfLogId());

            // 查询仅有第一结果集的处理
            if (log.isFirstResult() && workFlowLogs.size() == 1) {
                String firstMsgCode = log.getFirstResult().getMsgCode();

                if (trackerList.get(0).getWfStatus().equals(WorkFlowConstant.WF_STATUS_BEGIN) ||
                        trackerList.get(0).getWfStatus().equals(WorkFlowConstant.WF_STATUS_RUNNING)) {
                    // 工作单元未运行或仍在运行，没有查到工作日志记录，则将进度状态设置为运行中。这种状态是不明确的。
                    taskStatus = String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING);

                    if (!"0".equals(firstMsgCode)) {
                        // 第一结果集明确失败了，则需返回失败
                        taskStatus = String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_FAILED);
                        errCode = firstMsgCode;
                        errMsg = log.getFirstResult().getMsgInfo();
                    }

                } else {
                    // 工作单元已经结束了，仍没有查到工作日志记录，则将进度状态设置为失败
                    taskStatus = String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_FAILED);
                    if ("0".equals(firstMsgCode)) {
                        // 对于日志中第一结果集记为运行中的，则需要设置为失败
                        errCode = "10000";
                        errMsg = "运行不一致!";
                    }
                }
                WorkFlowLogScheduleResult result = new WorkFlowLogScheduleResult();
                result.setTaskStatus(taskStatus);
                result.setTaskSchedule("0");
                result.setWfLogId(0L);
                result.setWfGuid(tacker.getWfGuid());
                result.setErrCode(errCode);
                result.setErrInfo(errMsg);
                result.setWfStatus(tacker.getWfStatus());
                rt.add(result);
                continue;
            }

            while (log.fetchRow()) {
                WorkFlowLogScheduleResult result = new WorkFlowLogScheduleResult();
                result.setTaskStatus(log.getColValue("TASK_STATUS"));
                result.setTaskSchedule(log.getColValue("TASK_SCHEDULE"));
                result.setWfLogId(item.getWfLogId());
                result.setWfGuid(item.getWfGuid());
                result.setErrCode(log.getColValue("ERR_CODE"));
                result.setErrInfo(log.getColValue("ERR_INFO"));
                result.setWfStatus(tacker.getWfStatus());

                if (count == workFlowLogs.size()) {
                    // need to check last record status
                    if(tacker.getWfStatus().equals(WorkFlowConstant.WF_STATUS_BEGIN) ||
                            tacker.getWfStatus().equals(WorkFlowConstant.WF_STATUS_RUNNING)) {
                        // 工作单元未运行或仍在运行，返回正常日志记录情况即可。
                    } else {
                        // 工作单元已经结束了，检查工作单元日志进度状态，如果是仍于未完成时，这时需要处理为失败状态
                        if (String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_START).equals(result.getTaskStatus()) || String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING).equals(result.getTaskStatus())) {
                            result.setTaskStatus(WorkFlowConstant.WF_SCHEDULE_STATUS_FAILED.toString());
                            result.setErrCode("10000");
                            result.setErrInfo("运行不一致!");
                        }
                    }
                }
                rt.add(result);
            }
        }

        if (rt.size() <= 0) {
            // 检查工作单元工作状态
            if (tacker.getWfStatus().equals(WorkFlowConstant.WF_STATUS_BEGIN) ||
                    tacker.getWfStatus().equals(WorkFlowConstant.WF_STATUS_RUNNING)) {
                // 工作单元未运行或仍在运行，没有查到工作日志记录，则将进度状态设置为运行中。这种状态是不明确的。
                taskStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING.toString();
            } else {
                // 工作单元已经结束了，仍没有查到工作日志记录，则将进度状态设置为失败
                taskStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_FAILED.toString();
                errCode = "10000";
                errMsg = "运行不一致!";
            }
            WorkFlowLogScheduleResult result = new WorkFlowLogScheduleResult();
            result.setTaskStatus(taskStatus);
            result.setTaskSchedule("0");
            result.setWfLogId(0L);
            result.setWfGuid(tacker.getWfGuid());
            result.setErrCode(errCode);
            result.setErrInfo(errMsg);
            result.setWfStatus(tacker.getWfStatus());
            rt.add(result);
        }
        return rt;
    }


}
