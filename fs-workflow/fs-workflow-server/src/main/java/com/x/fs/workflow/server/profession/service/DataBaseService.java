package com.x.fs.workflow.server.profession.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.workflow.api.dto.WorkFlowRunnerParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerResult;
import com.x.fs.workflow.server.inter.IDoWorkFlowRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author x
 */
@Component
public class DataBaseService {

    @Autowired
    private IDoWorkFlowRunner doWorkFlowRunner;

    public WorkFlowRunnerResult dataBaseBackUp(DataBaseBackUpDTO inputParam) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParam;
        try {
            jsonParam = objectMapper.writeValueAsString(inputParam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FsServiceException("数据转换JSON格式失败");
        }
        // 构建参数调用工作流程执行
        WorkFlowRunnerParam param = new WorkFlowRunnerParam();
        param.setWfName("数据库备份[" + inputParam.getDatabaseName() + "]");
        param.setWfParentGuid("");
        param.setWfRequestText(jsonParam);
        param.setWfFunction("dataBaseBackUpWorkFlow");

        return doWorkFlowRunner.runWorkFlow(param);
    }


}
