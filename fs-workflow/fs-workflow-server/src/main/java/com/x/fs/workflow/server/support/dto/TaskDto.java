package com.x.fs.workflow.server.support.dto;

import java.io.Serializable;

/**
 * @author x
 */
public class TaskDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer taskDate;
    private Integer taskJobSn;
    private Integer taskStage;
    private Integer tasks;
    private String  taskStatus;
    private Integer taskSubSn;
    private String  beginSn;
    private String  endSn;
    private String  taskContent;
    private String  taskOperaMode;
    /**
     * 原始入参串 json格式，后续业务中如有不满基本参数时可从此串进行解析。
     */
    private String  originParam;

    public Integer getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Integer taskDate) {
        this.taskDate = taskDate;
    }

    public Integer getTaskJobSn() {
        return taskJobSn;
    }

    public void setTaskJobSn(Integer taskJobSn) {
        this.taskJobSn = taskJobSn;
    }

    public Integer getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(Integer taskStage) {
        this.taskStage = taskStage;
    }

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskSubSn() {
        return taskSubSn;
    }

    public void setTaskSubSn(Integer taskSubSn) {
        this.taskSubSn = taskSubSn;
    }

    public String getBeginSn() {
        return beginSn;
    }

    public void setBeginSn(String beginSn) {
        this.beginSn = beginSn;
    }

    public String getEndSn() {
        return endSn;
    }

    public void setEndSn(String endSn) {
        this.endSn = endSn;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskOperaMode() {
        return taskOperaMode;
    }

    public void setTaskOperaMode(String taskOperaMode) {
        this.taskOperaMode = taskOperaMode;
    }

    public String getOriginParam() {
        return originParam;
    }

    public void setOriginParam(String originParam) {
        this.originParam = originParam;
    }
}
