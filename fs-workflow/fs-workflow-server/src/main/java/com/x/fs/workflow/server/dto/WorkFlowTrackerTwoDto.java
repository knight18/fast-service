package com.x.fs.workflow.server.dto;

/**
 * @author x
 */
public class WorkFlowTrackerTwoDto {
    private String wfGuid;

    private String wfStatus;
    private Integer wfSignal;
    private Integer wfLeaseCount;
    private  long expiryTime;

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

    public Integer getWfSignal() {
        return wfSignal;
    }

    public void setWfSignal(Integer wfSignal) {
        this.wfSignal = wfSignal;
    }

    public Integer getWfLeaseCount() {
        return wfLeaseCount;
    }

    public void setWfLeaseCount(Integer wfLeaseCount) {
        this.wfLeaseCount = wfLeaseCount;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
