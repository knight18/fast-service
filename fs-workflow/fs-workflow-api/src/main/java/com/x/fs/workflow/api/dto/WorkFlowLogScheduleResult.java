package com.x.fs.workflow.api.dto;

import java.io.Serializable;

/**
 * @author x
 */
public class WorkFlowLogScheduleResult  implements Serializable {
    private static final long serialVersionUID = 1L;

    private long wfLogId;
    private String wfGuid;
    private String wfStatus;

    private String taskStatus;
    private String taskSchedule;
    private String errCode;
    private String errInfo;


    public long getWfLogId() {
        return wfLogId;
    }

    public void setWfLogId(long wfLogId) {
        this.wfLogId = wfLogId;
    }

    public String getWfGuid() {
        return wfGuid;
    }

    public void setWfGuid(String wfGuid) {
        this.wfGuid = wfGuid;
    }

    public String getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(String wfStatus) {
        this.wfStatus = wfStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskSchedule() {
        return taskSchedule;
    }

    public void setTaskSchedule(String taskSchedule) {
        this.taskSchedule = taskSchedule;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }
}
