package com.x.fs.workflow.server.dto;

import com.x.fs.mbg.model.FsWorkFlowTracker;

public class WorkFlowTrackerOneDto extends FsWorkFlowTracker {
    /**
     * 过期时间
     */
    private  long expiryTime;

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }



}
