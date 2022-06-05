package com.x.fs.mbg.model;

import java.io.Serializable;

public class FsTask implements Serializable {
    private Integer tasks;

    private Integer taskPhase;

    private String tasksName;

    private String taskJobType;

    private String multithread;

    private String preproc;

    private String mainproc;

    private String tailproc;

    private Integer timeout;

    private Integer threadNum;

    private Integer threadParNum;

    private Integer ordinal;

    private String extPara;

    private String taskBeanName;

    private static final long serialVersionUID = 1L;

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }

    public Integer getTaskPhase() {
        return taskPhase;
    }

    public void setTaskPhase(Integer taskPhase) {
        this.taskPhase = taskPhase;
    }

    public String getTasksName() {
        return tasksName;
    }

    public void setTasksName(String tasksName) {
        this.tasksName = tasksName;
    }

    public String getTaskJobType() {
        return taskJobType;
    }

    public void setTaskJobType(String taskJobType) {
        this.taskJobType = taskJobType;
    }

    public String getMultithread() {
        return multithread;
    }

    public void setMultithread(String multithread) {
        this.multithread = multithread;
    }

    public String getPreproc() {
        return preproc;
    }

    public void setPreproc(String preproc) {
        this.preproc = preproc;
    }

    public String getMainproc() {
        return mainproc;
    }

    public void setMainproc(String mainproc) {
        this.mainproc = mainproc;
    }

    public String getTailproc() {
        return tailproc;
    }

    public void setTailproc(String tailproc) {
        this.tailproc = tailproc;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public Integer getThreadParNum() {
        return threadParNum;
    }

    public void setThreadParNum(Integer threadParNum) {
        this.threadParNum = threadParNum;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getExtPara() {
        return extPara;
    }

    public void setExtPara(String extPara) {
        this.extPara = extPara;
    }

    public String getTaskBeanName() {
        return taskBeanName;
    }

    public void setTaskBeanName(String taskBeanName) {
        this.taskBeanName = taskBeanName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tasks=").append(tasks);
        sb.append(", taskPhase=").append(taskPhase);
        sb.append(", tasksName=").append(tasksName);
        sb.append(", taskJobType=").append(taskJobType);
        sb.append(", multithread=").append(multithread);
        sb.append(", preproc=").append(preproc);
        sb.append(", mainproc=").append(mainproc);
        sb.append(", tailproc=").append(tailproc);
        sb.append(", timeout=").append(timeout);
        sb.append(", threadNum=").append(threadNum);
        sb.append(", threadParNum=").append(threadParNum);
        sb.append(", ordinal=").append(ordinal);
        sb.append(", extPara=").append(extPara);
        sb.append(", taskBeanName=").append(taskBeanName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}