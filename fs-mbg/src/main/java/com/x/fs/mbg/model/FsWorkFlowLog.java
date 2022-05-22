package com.x.fs.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class FsWorkFlowLog implements Serializable {
    private Long wfLogId;

    private String wfGuid;

    private Date wfTime;

    private String wfLogText;

    private static final long serialVersionUID = 1L;

    public Long getWfLogId() {
        return wfLogId;
    }

    public void setWfLogId(Long wfLogId) {
        this.wfLogId = wfLogId;
    }

    public String getWfGuid() {
        return wfGuid;
    }

    public void setWfGuid(String wfGuid) {
        this.wfGuid = wfGuid;
    }

    public Date getWfTime() {
        return wfTime;
    }

    public void setWfTime(Date wfTime) {
        this.wfTime = wfTime;
    }

    public String getWfLogText() {
        return wfLogText;
    }

    public void setWfLogText(String wfLogText) {
        this.wfLogText = wfLogText;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wfLogId=").append(wfLogId);
        sb.append(", wfGuid=").append(wfGuid);
        sb.append(", wfTime=").append(wfTime);
        sb.append(", wfLogText=").append(wfLogText);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}