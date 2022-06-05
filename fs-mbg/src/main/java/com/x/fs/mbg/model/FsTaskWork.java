package com.x.fs.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class FsTaskWork implements Serializable {
    private Integer taskDate;

    private Integer taskWorkSn;

    private String taskWorkType;

    private String taskWorkStatus;

    private Integer taskPhase;

    private Date bgnTime;

    private Date endTime;

    private String runMode;

    private Long opCode;

    private String opName;

    private String opSite;

    private String workExt1;

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

    public String getTaskWorkType() {
        return taskWorkType;
    }

    public void setTaskWorkType(String taskWorkType) {
        this.taskWorkType = taskWorkType;
    }

    public String getTaskWorkStatus() {
        return taskWorkStatus;
    }

    public void setTaskWorkStatus(String taskWorkStatus) {
        this.taskWorkStatus = taskWorkStatus;
    }

    public Integer getTaskPhase() {
        return taskPhase;
    }

    public void setTaskPhase(Integer taskPhase) {
        this.taskPhase = taskPhase;
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

    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }

    public Long getOpCode() {
        return opCode;
    }

    public void setOpCode(Long opCode) {
        this.opCode = opCode;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpSite() {
        return opSite;
    }

    public void setOpSite(String opSite) {
        this.opSite = opSite;
    }

    public String getWorkExt1() {
        return workExt1;
    }

    public void setWorkExt1(String workExt1) {
        this.workExt1 = workExt1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskDate=").append(taskDate);
        sb.append(", taskWorkSn=").append(taskWorkSn);
        sb.append(", taskWorkType=").append(taskWorkType);
        sb.append(", taskWorkStatus=").append(taskWorkStatus);
        sb.append(", taskPhase=").append(taskPhase);
        sb.append(", bgnTime=").append(bgnTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", runMode=").append(runMode);
        sb.append(", opCode=").append(opCode);
        sb.append(", opName=").append(opName);
        sb.append(", opSite=").append(opSite);
        sb.append(", workExt1=").append(workExt1);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}