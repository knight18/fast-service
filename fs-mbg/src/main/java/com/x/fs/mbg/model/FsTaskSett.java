package com.x.fs.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class FsTaskSett implements Serializable {
    private Integer taskDate;

    private Integer taskWorkSn;

    private Integer taskPhase;

    private String wfGuid;

    private String taskPhaseName;

    private String taskStatus;

    private String taskContent;

    private Integer errCode;

    private String errDesc;

    private Date bgnTime;

    private Date endTime;

    private static final long serialVersionUID = 1L;

    public Integer getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Integer taskDate) {
        this.taskDate = taskDate;
    }

    public Integer getTaskWorkSn() {
        return taskWorkSn;
    }

    public void setTaskWorkSn(Integer taskWorkSn) {
        this.taskWorkSn = taskWorkSn;
    }

    public Integer getTaskPhase() {
        return taskPhase;
    }

    public void setTaskPhase(Integer taskPhase) {
        this.taskPhase = taskPhase;
    }

    public String getWfGuid() {
        return wfGuid;
    }

    public void setWfGuid(String wfGuid) {
        this.wfGuid = wfGuid;
    }

    public String getTaskPhaseName() {
        return taskPhaseName;
    }

    public void setTaskPhaseName(String taskPhaseName) {
        this.taskPhaseName = taskPhaseName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public Date getBgnTime() {
        return bgnTime;
    }

    public void setBgnTime(Date bgnTime) {
        this.bgnTime = bgnTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskDate=").append(taskDate);
        sb.append(", taskWorkSn=").append(taskWorkSn);
        sb.append(", taskPhase=").append(taskPhase);
        sb.append(", wfGuid=").append(wfGuid);
        sb.append(", taskPhaseName=").append(taskPhaseName);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", taskContent=").append(taskContent);
        sb.append(", errCode=").append(errCode);
        sb.append(", errDesc=").append(errDesc);
        sb.append(", bgnTime=").append(bgnTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}