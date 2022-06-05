package com.x.fs.workflow.server.support.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.workflow.server.inter.IDoWorkThreadHelper;
import com.x.fs.workflow.server.inter.IWorkFlowLogger;
import com.x.fs.workflow.server.support.dto.TaskDto;
import com.x.fs.workflow.server.support.utils.TaskHelper;
import com.x.fs.workflow.server.utils.WorkFlowLogSchedule;


/**
 * @author x
 */
public abstract class TaskService {

    public void beforeTask(String param, IDoWorkThreadHelper workThreadHelper) {
        TaskDto taskDto = this.parserParam(param, workThreadHelper, "before");
        if (taskDto == null) {
            return;
        }
        try {
            TaskHelper helper = new TaskHelper(taskDto);
            helper.settTaskStart();
            doBeforeTaskEvent(taskDto);
        } catch (Exception e) {
            if (!(e instanceof FsServiceException)) {
                workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "清算子任务前处理出错!" + e.getMessage());
                return;
            }
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "清算子任务前处理出错!" + e.getMessage());
            return;
        }
        workThreadHelper.getWorkFlowLogger().setFirstResult("0", "清算子任务前处理完成!");
    }

    public void process(String param, IDoWorkThreadHelper workThreadHelper) {
        TaskDto taskDto = this.parserParam(param, workThreadHelper, "process");
        if (param == null) {
            return;
        }
        workThreadHelper.getWorkFlowLogger().setFirstResult("0", "清算任务准备处理正常");
        WorkFlowLogSchedule schedule = new WorkFlowLogSchedule(workThreadHelper.getWorkFlowLogger());

        schedule.start("清算任务处理开始");
        try {
            doTask(taskDto, schedule, workThreadHelper);
        } catch (Exception e) {

            schedule.error("-1", "清算任务处理出错!" + e.getMessage());
            return;
        }
    }

    public void afterTask(String param, IDoWorkThreadHelper workThreadHelper) {
        TaskDto settTaskParam = this.parserParam(param, workThreadHelper, "after");
        if (settTaskParam == null) {
            return;
        }
        try {
            TaskHelper helper = new TaskHelper(settTaskParam);
            helper.settTaskEnd();

            doAfterTaskEvent(settTaskParam);
        } catch (Exception e) {
            if (!(e instanceof FsServiceException)) {
                workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "清算任务后处理出错!" + e.getMessage());
                return;
            }
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "清算任务后处理出错!" + e.getMessage());
            return;
        }
        workThreadHelper.getWorkFlowLogger().setFirstResult("0", "清算任务后处理完成!");
    }

    public void listTaskZone(String param, IDoWorkThreadHelper workThreadHelper) {
        TaskDto settTaskParam = this.parserParam(param, workThreadHelper, "listZone");
        if (settTaskParam == null) {
            return;
        }
        try {
            listTaskZone(settTaskParam, workThreadHelper);
        } catch (Exception e) {
            if (!(e instanceof FsServiceException)) {
                workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "分段区间查询出错!" + e.getMessage());
                return;
            }
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "分段区间查询出错!" + e.getMessage());
            return;
        }
    }

    private TaskDto parserParam(String param, IDoWorkThreadHelper workThreadHelper, String event) {
        TaskDto taskDto = new TaskDto();
        taskDto.setOriginParam(param);
        JSONObject jsonObject;
        try {
            jsonObject = JSON.parseObject(param);
        } catch (Exception e) {
            e.printStackTrace();
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "解析参数失败!" + param);
            return null;
        }
        StringBuilder lessParam = new StringBuilder();

        try {
            taskDto.setTaskDate(Integer.valueOf(jsonObject.getString("TASK_DATE")));
            taskDto.setTaskJobSn(Integer.valueOf(jsonObject.getString("TASK_JOB_SN")));
            taskDto.setTaskStage(Integer.valueOf(jsonObject.getString("TASK_STAGE")));
            taskDto.setTasks(Integer.valueOf(jsonObject.getString("TASKS")));
            switch (event) {
                case "after":
                    // 任务状态
                    taskDto.setTaskStatus(jsonObject.getString("TASK_STATUS"));
                    // 任务内容
                    taskDto.setTaskContent(jsonObject.getString("TASK_CONTENT"));
                    if (taskDto.getTaskStatus() == null) {
                        lessParam.append("TASK_STATUS ");
                    }
                    break;
                case "process":
                    taskDto.setTaskSubSn(Integer.valueOf(jsonObject.getString("TASK_SUB_SN")));
                    taskDto.setBeginSn(jsonObject.getString("BEGIN_SN"));
                    taskDto.setEndSn(jsonObject.getString("END_SN"));
                    if (taskDto.getTaskSubSn() == null) {
                        lessParam.append("TASK_SUB_SN ");
                    }
                    if (taskDto.getBeginSn() == null) {
                        lessParam.append("BEGIN_SN ");
                    }
                    if (taskDto.getEndSn() == null) {
                        lessParam.append("END_SN ");
                    }
                    break;
                default:
                    break;
            }

            if (taskDto.getTaskDate() == null) {
                lessParam.append("TASK_DATE ");
            }
            if (taskDto.getTasks() == null) {
                lessParam.append("TASKS");
            }
            if (taskDto.getTaskJobSn() == null) {
                lessParam.append("TASK_JOB_SN ");
            }
            if (taskDto.getTaskStage() == null) {
                lessParam.append("TASK_STAGE ");
            }
        } catch (Exception e) {
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "解析参数失败! param:" + param + "err:" + e.getMessage());
            return null;
        }
        if (lessParam.length() > 0) {
            workThreadHelper.getWorkFlowLogger().setFirstResult("-1", "缺少必要参数" + lessParam);
            return null;
        }
        return taskDto;
    }

    /**
     * 工作任务前处理，处理出错抛 Exception异常出来结束
     */
    protected abstract void doBeforeTaskEvent(TaskDto taskDto);

    /**
     * 工作任务处理，处理出错抛 Exception异常出来结束
     */
    protected abstract void doTask(TaskDto taskDto, WorkFlowLogSchedule schedule, IDoWorkThreadHelper workThreadHelper);


    /**
     * 工作任务后处理，处理出错抛 Exception异常出来结束
     */
    protected abstract void doAfterTaskEvent(TaskDto taskDto);

    /**
     * 区间段查询，处理出错抛 Exception异常出来结束。正常时需先写第一结果，再写第二结果集
     * 如：
     * workFlowLogger.setFirstResult("0", "默认实现查询分段区间信息");
     * 第二结果集。
     */
    protected void listTaskZone(TaskDto taskDto, IDoWorkThreadHelper workThreadHelper) {
        IWorkFlowLogger workFlowLogger = workThreadHelper.getWorkFlowLogger();
        workFlowLogger.setFirstResult("0", "默认实现查询分段区间信息");
    }

}
