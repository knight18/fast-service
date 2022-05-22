package com.x.fs.workflow.server.inter;


import com.x.fs.workflow.api.dto.WorkFlowRunnerParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerResult;

/**
 * @author x
 */
public interface IDoWorkFlowRunner {

    WorkFlowRunnerResult runWorkUnit(WorkFlowRunnerParam inputParam);


}
