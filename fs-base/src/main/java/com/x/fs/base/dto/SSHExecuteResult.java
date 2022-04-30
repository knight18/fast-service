package com.x.fs.base.dto;

import java.io.Serializable;

/**
 * @author x
 */
public class SSHExecuteResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String runLog;
    private String errLog;
    private Integer exitStatus;
    private Boolean closed;

    @Override
    public String toString() {
        return "SSHExecuteResult{" +
                "runLog='" + runLog + '\'' +
                ", errLog='" + errLog + '\'' +
                ", exitStatus=" + exitStatus +
                ", closed=" + closed +
                '}';
    }

    public String getRunLog() {
        return runLog;
    }

    public void setRunLog(String runLog) {
        this.runLog = runLog;
    }

    public String getErrLog() {
        return errLog;
    }

    public void setErrLog(String errLog) {
        this.errLog = errLog;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
