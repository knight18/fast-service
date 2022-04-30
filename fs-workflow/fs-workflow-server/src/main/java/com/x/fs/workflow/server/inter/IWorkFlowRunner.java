package com.x.fs.workflow.server.inter;

import com.x.fs.workflow.server.dto.WorkFlowRunnerParam;
import com.x.fs.workflow.server.dto.WorkFlowRunnerResult;

/**
 * @author x
 */
public interface IWorkFlowRunner {

    WorkFlowRunnerResult runWorkUnit(WorkFlowRunnerParam inputParam);


}
