package com.x.fs.workflow.server.utils;

import com.x.fs.base.utils.StringUtils;
import com.x.fs.workflow.server.constant.WorkFlowConstant;
import com.x.fs.workflow.server.inter.IWorkFlowLogger;

/**
 * @author x
 */
public class WorkFlowLogSchedule {

    private IWorkFlowLogger workFlowLogger;

    private static final int STATIC_SCHEDULE_MIN = 0;
    private static final int STATIC_SCHEDULE_MAX = 100;

    private Integer currentStep;
    private Integer currentStatus;

    public WorkFlowLogSchedule(IWorkFlowLogger workFlowLogger) {
        this.workFlowLogger = workFlowLogger;
        this.currentStep = 0;
        this.currentStatus = -1;
    }

    public void start(String msg) {
        if (this.currentStatus != -1) {
            return;
        }
        this.currentStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_START;
        this.saveStep(String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_START), STATIC_SCHEDULE_MIN, "0", msg == null ? "业务处理开始" : msg);
    }

    public void step(Integer stepLen, String msgCode, String msg) {
        if (!String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_START).equals(this.currentStatus.toString()) && !String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING).equals(this.currentStatus.toString())) {
            return;
        }
        if (!String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_START).equals(this.currentStatus.toString())) {
            this.currentStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_START;
        }
        if (this.currentStep + stepLen < STATIC_SCHEDULE_MIN) {
            this.currentStep = STATIC_SCHEDULE_MIN;
        } else if (this.currentStep + stepLen >= STATIC_SCHEDULE_MAX) {
            this.currentStep = STATIC_SCHEDULE_MAX - 1;
        } else {
            this.currentStep += stepLen;
        }
        this.saveStep(String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_START), this.currentStep, msgCode, msg == null ? "业务运行中..." : msg);
    }

    public void finish(String msg) {
        if (!WorkFlowConstant.WF_SCHEDULE_STATUS_START.equals(this.currentStatus) && !String.valueOf(WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING).equals(this.currentStatus.toString())) {
            return;
        }
        if (this.currentStep < STATIC_SCHEDULE_MAX) {
            this.step(STATIC_SCHEDULE_MAX - this.currentStep, "0", null);
        }
        this.currentStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_SUCCESS;
        this.saveStep(WorkFlowConstant.WF_SCHEDULE_STATUS_SUCCESS.toString(), STATIC_SCHEDULE_MAX, "0", msg == null ? "业务处理完成!" : msg);
    }

    public void cancel(String msg) {
        if (!this.currentStatus.equals(WorkFlowConstant.WF_SCHEDULE_STATUS_START) && !this.currentStatus.equals(WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING)) {
            return;
        }
        this.currentStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_CANCEL;
        this.saveStep(WorkFlowConstant.WF_SCHEDULE_STATUS_CANCEL.toString(), this.currentStep, "0", msg == null ? "业务处理完成!" : msg);
    }

    public void error(String msgCode, String msg) {
        if (!WorkFlowConstant.WF_SCHEDULE_STATUS_START.equals(this.currentStatus) && !WorkFlowConstant.WF_SCHEDULE_STATUS_RUNNING.equals(this.currentStatus)) {
            return;
        }
        this.currentStatus = WorkFlowConstant.WF_SCHEDULE_STATUS_FAILED;
        this.saveStep(WorkFlowConstant.WF_SCHEDULE_STATUS_FAILED.toString(), ++this.currentStep, msgCode, msg == null ? "业务处理异常!" : msg);
    }

    private void saveStep(String taskStatus, Integer stepLen, String msgCode, String msg) {
        this.workFlowLogger.setSecondResultBegin(4, "TASK_STATUS, TASK_SCHEDULE, ERR_CODE, ERR_INFO");
        this.workFlowLogger.addRow();
        // 库中WU_LOG_TEXT总共支持长2048,因此需要限制错误内容长度，否则会造成日志保存不了。
        this.workFlowLogger.setColValue("ERR_INFO", StringUtils.subStringByByte(msg, 1900));
        this.workFlowLogger.setColValue("TASK_SCHEDULE", stepLen.toString());
        this.workFlowLogger.setColValue("ERR_CODE", msgCode);
        this.workFlowLogger.setColValue("TASK_STATUS", taskStatus);
        this.workFlowLogger.saveRow();
        this.workFlowLogger.setSecondResultEnd();
    }

}
