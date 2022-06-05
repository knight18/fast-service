package com.x.fs.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class FsTaskDetail implements Serializable {
    private Integer taskDate;

    private Integer taskWorkSn;

    private Integer taskStage;

    private Integer fsTask;

    private String fsTaskName;

    private String taskPhaseName;

    private String taskStatus;

    private String taskContent;

    private Integer taskSchedule;

    private Integer errCode;

    private String errMsg;

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

    public Integer getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(Integer taskStage) {
        this.taskStage = taskStage;
    }

    public Integer getFsTask() {
        return fsTask;
    }

    public void setFsTask(Integer fsTask) {
        this.fsTask = fsTask;
    }

    public String getFsTaskName() {
        return fsTaskName;
    }

    public void setFsTaskName(String fsTaskName) {
        this.fsTaskName = fsTaskName;
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

    public Integer getTaskSchedule() {
        return taskSchedule;
    }

    public void setTaskSchedule(Integer taskSchedule) {
        this.taskSchedule = taskSchedule;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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
        sb.append(", taskStage=").append(taskStage);
        sb.append(", fsTask=").append(fsTask);
        sb.append(", fsTaskName=").append(fsTaskName);
        sb.append(", taskPhaseName=").append(taskPhaseName);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", taskContent=").append(taskContent);
        sb.append(", taskSchedule=").append(taskSchedule);
        sb.append(", errCode=").append(errCode);
        sb.append(", errMsg=").append(errMsg);
        sb.append(", bgnTime=").append(bgnTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}