package com.x.fs.workflow.server.dto;

import java.io.Serializable;

/**
 * @author x
 */
public class WorkFlowTrackerDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private String wfParentGuid;

    private String wfName;

    private String wfGuid;

    private long wfLeaseTime;

    private String wfStatus;

    private long wfGraceTime;

    private String wfRequestText;

    private int wfSignal;

    private String wfFunction;

    private int wfLeaseCount;

    public String getWfParentGuid() {
        return wfParentGuid;
    }

    public void setWfParentGuid(String wfParentGuid) {
        this.wfParentGuid = wfParentGuid;
    }

    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }

    public String getWfGuid() {
        return wfGuid;
    }

    public void setWfGuid(String wfGuid) {
        this.wfGuid = wfGuid;
    }

    public long getWfLeaseTime() {
        return wfLeaseTime;
    }

    public void setWfLeaseTime(long wfLeaseTime) {
        this.wfLeaseTime = wfLeaseTime;
    }

    public String getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(String wfStatus) {
        this.wfStatus = wfStatus;
    }

    public long getWfGraceTime() {
        return wfGraceTime;
    }

    public void setWfGraceTime(long wfGraceTime) {
        this.wfGraceTime = wfGraceTime;
    }

    public String getWfRequestText() {
        return wfRequestText;
    }

    public void setWfRequestText(String wfRequestText) {
        this.wfRequestText = wfRequestText;
    }

    public int getWfSignal() {
        return wfSignal;
    }

    public void setWfSignal(int wfSignal) {
        this.wfSignal = wfSignal;
    }

    public String getWfFunction() {
        return wfFunction;
    }

    public void setWfFunction(String wfFunction) {
        this.wfFunction = wfFunction;
    }

    public int getWfLeaseCount() {
        return wfLeaseCount;
    }

    public void setWfLeaseCount(int wfLeaseCount) {
        this.wfLeaseCount = wfLeaseCount;
    }
}
