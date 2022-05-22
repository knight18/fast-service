package com.x.fs.workflow.server.inter;

import com.x.fs.base.dto.ContextDTO;

/**
 * @author x
 */
public interface IDoWorkThreadHelper {

    /**
     * 调度执行框架在接受到工作流程退出信号时会设置退出信号。
     * 因此业务单元对于长时间循环执行的任务，每次循环时先调用此方法来检查是否要退出。
     * @param
     * @return 返回boolean true表示可继续进行处理。其它要退出
     */
    boolean continueLoop();

    /**
     * @return 返回当前工作流程GUID
     */
    String getWfGuid();

    /**
     * 工作流程执行业务日志，由业务单元主动调用记录业务日志.如需使用进度可结合WorkFlowSchedule类来进处理。
     * 参照示例如：
     * WorkFlowLogger wfLogger = workThreadHelper.getWorkFlowLogger();
     * wfLogger.setFirstResult("0","processing");
     * <p>
     * WorkFlowLogSchedule wfSchedule = new WorkFlowLogSchedule(wfLogId);
     * wfSchedule.start("工作单元处理开始");
     * wfSchedule.step(5,"0","工作单元处理中...");
     * wfSchedule.finish("工作单元处理结束");
     *
     * @param
     * @return 返回boolean
     */
    IWorkFlowLogger getWorkFlowLogger();

    /**
     * 业务完成在提交事务前时进行检查，如果是返回false表示当前工作单超过租约期，外部已发送强制终止工作单元指令，当前的业务必须终止掉。
     *
     * @return
     */
    boolean dbFence();

    ContextDTO getContext();

}
