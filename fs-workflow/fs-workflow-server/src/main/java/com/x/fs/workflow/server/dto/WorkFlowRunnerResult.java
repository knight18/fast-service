package com.x.fs.workflow.server.dto;

public class WorkFlowRunnerResult {

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
