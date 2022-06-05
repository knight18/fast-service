package com.x.fs.workflow.server.support.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * @author x
 */
public class TaskWorkFlowDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务时间
     */
    private Integer taskDate;
    /**
     * 任务工作编号
     */
    private Integer taskJobSn;
    /**
     * 工作阶段
     */
    private Integer taskStage;
    /**
     * 任务
     */
    private Integer tasks;
    /**
     * 机构编号
     */
    private Integer intOrg;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 任务bean
     */
    private String taskBean;
    /**
     * 任务参数
     */
    private String taskParam;
    /**
     * 任务顺序
     */
    private Integer taskStep;

    public Integer getTaskDate() {
        return taskDate;
    }

    @JsonSetter("TASK_DATE")
    public void setTaskDate(Integer taskDate) {
        this.taskDate = taskDate;
    }

    public Integer getTaskJobSn() {
        return taskJobSn;
    }

    @JsonSetter("TASK_JOB_SN")
    public void setTaskJobSn(Integer taskJobSn) {
        this.taskJobSn = taskJobSn;
    }

    public Integer getTaskStage() {
        return taskStage;
    }

    @JsonSetter("TASK_STAGE")
    public void setTaskStage(Integer taskStage) {
        this.taskStage = taskStage;
    }

    public Integer getTasks() {
        return tasks;
    }
    @JsonSetter("TASKS")
    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }

    public Integer getIntOrg() {
        return intOrg;
    }

    @JsonSetter("INT_ORG")
    public void setIntOrg(Integer intOrg) {
        this.intOrg = intOrg;
    }

    public String getTaskContent() {
        return taskContent;
    }

    @JsonSetter("TASK_CONTENT")
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskBean() {
        return taskBean;
    }

    @JsonSetter("TASK_BEAN")
    public void setTaskBean(String taskBean) {
        this.taskBean = taskBean;
    }

    public String getTaskParam() {
        return taskParam;
    }

    @JsonSetter("TASK_PARAM")
    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
    }

    public Integer getTaskStep() {
        return taskStep;
    }

    @JsonSetter("TASK_STEP")
    public void setTaskStep(Integer taskStep) {
        this.taskStep = taskStep;
    }
}
