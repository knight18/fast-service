package com.x.fs.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class FsWorkFlowTracker implements Serializable {
    private String wfGuid;

    private String wfName;

    private Date wfCreateTime;

    private Long wfLeaseTime;

    private Long wfGraceTime;

    private Date wfLeaseExpiryTime;

    private Integer wfLeaseCount;

    private String wfRequestText;

    private String wfStatus;

    private Integer wfSignal;

    private String wfParentGuid;

    private Date wfFinishTime;

    private String wfFunction;

    private static final long serialVersionUID = 1L;

    public String getWfGuid() {
        return wfGuid;
    }

    public void setWfGuid(String wfGuid) {
        this.wfGuid = wfGuid;
    }

    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }

    public Date getWfCreateTime() {
        return wfCreateTime;
    }

    public void setWfCreateTime(Date wfCreateTime) {
        this.wfCreateTime = wfCreateTime;
    }

    public Long getWfLeaseTime() {
        return wfLeaseTime;
    }

    public void setWfLeaseTime(Long wfLeaseTime) {
        this.wfLeaseTime = wfLeaseTime;
    }

    public Long getWfGraceTime() {
        return wfGraceTime;
    }

    public void setWfGraceTime(Long wfGraceTime) {
        this.wfGraceTime = wfGraceTime;
    }

    public Date getWfLeaseExpiryTime() {
        return wfLeaseExpiryTime;
    }

    public void setWfLeaseExpiryTime(Date wfLeaseExpiryTime) {
        this.wfLeaseExpiryTime = wfLeaseExpiryTime;
    }

    public Integer getWfLeaseCount() {
        return wfLeaseCount;
    }

    public void setWfLeaseCount(Integer wfLeaseCount) {
        this.wfLeaseCount = wfLeaseCount;
    }

    public String getWfRequestText() {
        return wfRequestText;
    }

    public void setWfRequestText(String wfRequestText) {
        this.wfRequestText = wfRequestText;
    }

    public String getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(String wfStatus) {
        this.wfStatus = wfStatus;
    }

    public Integer getWfSignal() {
        return wfSignal;
    }

    public void setWfSignal(Integer wfSignal) {
        this.wfSignal = wfSignal;
    }

    public String getWfParentGuid() {
        return wfParentGuid;
    }

    public void setWfParentGuid(String wfParentGuid) {
        this.wfParentGuid = wfParentGuid;
    }

    public Date getWfFinishTime() {
        return wfFinishTime;
    }

    public void setWfFinishTime(Date wfFinishTime) {
        this.wfFinishTime = wfFinishTime;
    }

    public String getWfFunction() {
        return wfFunction;
    }

    public void setWfFunction(String wfFunction) {
        this.wfFunction = wfFunction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wfGuid=").append(wfGuid);
        sb.append(", wfName=").append(wfName);
        sb.append(", wfCreateTime=").append(wfCreateTime);
        sb.append(", wfLeaseTime=").append(wfLeaseTime);
        sb.append(", wfGraceTime=").append(wfGraceTime);
        sb.append(", wfLeaseExpiryTime=").append(wfLeaseExpiryTime);
        sb.append(", wfLeaseCount=").append(wfLeaseCount);
        sb.append(", wfRequestText=").append(wfRequestText);
        sb.append(", wfStatus=").append(wfStatus);
        sb.append(", wfSignal=").append(wfSignal);
        sb.append(", wfParentGuid=").append(wfParentGuid);
        sb.append(", wfFinishTime=").append(wfFinishTime);
        sb.append(", wfFunction=").append(wfFunction);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}