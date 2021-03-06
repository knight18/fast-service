package com.x.fs.workflow.server.constant;

/**
 * @author x
 */
public interface WorkFlowConstant {

    /**
     * 工作单元状态:创建
     */
    String WF_STATUS_BEGIN = "0";
    /**
     * 工作单元状态:运行中
     */
    String WF_STATUS_RUNNING = "1";
    /**
     * 工作单元状态:正常结束
     */
    String WF_STATUS_NORMAL_FINISH = "2";
    /**
     * 工作单元状态:中止运行
     */
    String WF_STATUS_ABORT_FINISH = "3";

    /**
     *
     */
    Integer WF_SIGNAL_NORMAL = 0;
    Integer WF_SIGNAL_FORCE_STOP = 1;

    /**
     * 开始
     */
    Integer WF_SCHEDULE_STATUS_START = 0;
    /**
     * 正在运行
     */
    Integer WF_SCHEDULE_STATUS_RUNNING = 1;
    /**
     * 处理成功
     */
    Integer WF_SCHEDULE_STATUS_SUCCESS = 2;
    /**
     * 处理失败
     */
    Integer WF_SCHEDULE_STATUS_FAILED = 3;
    /**
     * 取消
     */
    Integer WF_SCHEDULE_STATUS_CANCEL = 4;


    /**
     * 清算任务执行步骤 1 执行前处理
     */
    int WF_TASK_BEFORE_STEP = 1;
    /**
     * 2 执行过程
     */
    int WF_TASK_EXEC_STEP = 2;
    /**
     * 3 后处理
     */
    int WF_TASK_AFTER_STEP = 3;
    /**
     * 4 执行结束
     */
    int WF_TASK_SPLIT_STEP = 4;

}
