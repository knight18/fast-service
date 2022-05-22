package com.x.fs.workflow.api.dto;

import java.io.Serializable;

public class WorkFlowRunnerResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer retCode;
    private String workUid;

    public WorkFlowRunnerResult(Integer retCode,String workUid){
        this.retCode = retCode;
        this.workUid = workUid;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getWorkUid() {
        return workUid;
    }

    public void setWorkUid(String workUid) {
        this.workUid = workUid;
    }
}
