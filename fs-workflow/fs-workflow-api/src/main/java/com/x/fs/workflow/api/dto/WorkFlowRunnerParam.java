package com.x.fs.workflow.api.dto;

import java.io.Serializable;

/**
 * @author x
 */
public class WorkFlowRunnerParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String wfName;
    private String wfParentGuid;
    private String wfFunction;
    private String wfRequestText;
    private Integer wfGraceTime;

    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }

    public String getWfParentGuid() {
        return wfParentGuid;
    }

    public void setWfParentGuid(String wfParentGuid) {
        this.wfParentGuid = wfParentGuid;
    }

    public String getWfFunction() {
        return wfFunction;
    }

    public void setWfFunction(String wfFunction) {
        this.wfFunction = wfFunction;
    }

    public String getWfRequestText() {
        return wfRequestText;
    }

    public void setWfRequestText(String wfRequestText) {
        this.wfRequestText = wfRequestText;
    }

    public Integer getWfGraceTime() {
        return wfGraceTime;
    }

    public void setWfGraceTime(Integer wfGraceTime) {
        this.wfGraceTime = wfGraceTime;
    }
}
