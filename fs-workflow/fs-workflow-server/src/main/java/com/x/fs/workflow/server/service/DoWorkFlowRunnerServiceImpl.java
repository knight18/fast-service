package com.x.fs.workflow.server.service;

import com.x.fs.common.exception.FsServiceException;

import com.x.fs.workflow.api.dto.WorkFlowRunnerExtParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerResult;
import com.x.fs.workflow.server.dto.WorkFlowTrackerDto;
import com.x.fs.workflow.server.inter.IDoWorkFlowRunner;
import org.springframework.stereotype.Service;

/**
 * @author x
 */
@Service("iDoWorkFlowRunner")
public class DoWorkFlowRunnerServiceImpl implements IDoWorkFlowRunner{
    @Override
    public WorkFlowRunnerResult runWorkFlow(WorkFlowRunnerParam inputParam) {
        WorkFlowTrackerDto workFlowTracker;
        try {
            workFlowTracker = WorkFlowTrackerServiceImpl.getWorkFlowTracker().allocWorkFlow(inputParam);
        } catch (Exception e){
            throw new FsServiceException("allocWorkFlow error!" + e.getMessage());
        }
        if (workFlowTracker == null) {
            return new WorkFlowRunnerResult(-1,null);
        }
        return WorkFlowTrackerServiceImpl.getWorkFlowTracker().runWorkFlow(workFlowTracker);
    }

}
