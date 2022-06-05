package com.x.fs.workflow.server.support.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.workflow.server.constant.WorkFlowConstant;
import com.x.fs.workflow.server.inter.IDoWork;
import com.x.fs.workflow.server.inter.IDoWorkThreadHelper;
import com.x.fs.workflow.server.support.dto.TaskWorkFlowDto;
import org.springframework.stereotype.Service;

/**
 * @author x
 */
@Service("taskWorkFlowService")
public class TaskWorkFlowService implements IDoWork {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public int doWork(String param, IDoWorkThreadHelper workThreadHelper) {
        TaskWorkFlowDto taskWorkFlowDto;

        try {
            taskWorkFlowDto = OBJECT_MAPPER.readValue(param, TaskWorkFlowDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "转换参数出错!" + param);
            return -1;
        }

        TaskService taskService;
        try {
            taskService = FsApplicationContext.getBean(taskWorkFlowDto.getTaskBean());
        } catch (Exception e) {
            e.printStackTrace();
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "TASK_BEAN 查找失败!" + param + ",errMsg:" + e.getMessage());
            return -1;
        }

        try {
            switch (taskWorkFlowDto.getTaskStep()) {
                case WorkFlowConstant.WF_TASK_BEFORE_STEP:
                    taskService.beforeTask(taskWorkFlowDto.getTaskParam(), workThreadHelper);
                    break;
                case WorkFlowConstant.WF_TASK_EXEC_STEP:
                    taskService.process(taskWorkFlowDto.getTaskParam(), workThreadHelper);
                    break;
                case WorkFlowConstant.WF_TASK_AFTER_STEP:
                    taskService.afterTask(taskWorkFlowDto.getTaskParam(), workThreadHelper);
                    break;
                case WorkFlowConstant.WF_TASK_SPLIT_STEP:
                    taskService.listTaskZone(taskWorkFlowDto.getTaskParam(), workThreadHelper);
                    break;
                default:
                    workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "任务执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (0 == workThreadHelper.getWorkFlowLogger().getCurrentWuLogId()) {
                // 一笔日志都没有记就出现异常，这时需要补一笔进去
                workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "任务try-catch异常! " + e.getMessage());
            }
        }
        return 0;
    }
}
