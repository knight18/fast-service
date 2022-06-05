package com.x.fs.workflow.api.service;

import com.x.fs.workflow.api.dto.DataBaseBackUpParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerResult;

public interface DataBaseApi {

    WorkFlowRunnerResult dataBaseBackUp(DataBaseBackUpParam inputParam);

}
