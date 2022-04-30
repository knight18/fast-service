package com.x.fs.workflow.server.dto;

public class WorkFlowRunnerParam {
    private String wuName;
    private String wuParentGuid;
    private String wuFunction;
    /**
     * 。
     */
    private String wuRequestText;

    public String getWuName() {
        return wuName;
    }

    public void setWuName(String wuName) {
        this.wuName = wuName;
    }

    public String getWuParentGuid() {
        return wuParentGuid;
    }

    public void setWuParentGuid(String wuParentGuid) {
        this.wuParentGuid = wuParentGuid;
    }

    public String getWuFunction() {
        return wuFunction;
    }

    public void setWuFunction(String wuFunction) {
        this.wuFunction = wuFunction;
    }

    public String getWuRequestText() {
        return wuRequestText;
    }

    public void setWuRequestText(String wuRequestText) {
        this.wuRequestText = wuRequestText;
    }
}
