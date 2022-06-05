package com.x.fs.workflow.server.support.utils;

import com.x.fs.base.utils.DateUtils;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.mbg.model.FsTask;
import com.x.fs.mbg.model.FsTaskDetail;
import com.x.fs.mbg.model.FsTaskSett;
import com.x.fs.mbg.model.FsTaskWork;
import com.x.fs.workflow.server.atom.IFsTaskAtom;
import com.x.fs.workflow.server.atom.IFsTaskDetailAtom;
import com.x.fs.workflow.server.atom.IFsTaskSettAtom;
import com.x.fs.workflow.server.atom.IFsTaskWorkAtom;
import com.x.fs.workflow.server.constant.WorkFlowConstant;
import com.x.fs.workflow.server.support.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author x
 */
public class TaskHelper {

    @Autowired
    private IFsTaskWorkAtom fsTaskWorkAtom;

    @Autowired
    private IFsTaskSettAtom fsTaskSettAtom;

    @Autowired
    private IFsTaskDetailAtom fsTaskDetailAtom;

    @Autowired
    private IFsTaskAtom fsTaskAtom;

    private TaskDto taskDto;

    public TaskHelper(TaskDto taskDto) {
        this.taskDto = taskDto;
    }


    private FsTaskWork getTaskWork() {

        FsTaskWork fsTaskWork = fsTaskWorkAtom.selectByPrimaryKey(taskDto.getTaskDate(), taskDto.getTaskJobSn());
        if (fsTaskWork == null) {
            throw new FsServiceException("" + taskDto.getTaskJobSn());
        }
        return fsTaskWork;
    }

    private FsTaskSett getTaskSett() {
        FsTaskSett fsTaskSett = fsTaskSettAtom.selectByKey(taskDto.getTaskDate(), taskDto.getTaskJobSn(), taskDto.getTaskStage());
        if (fsTaskSett == null) {
            throw new FsServiceException("工作流程未开始TaskStage:[" + taskDto.getTaskStage() + "]");
        }
        return fsTaskSett;
    }


    public void settTaskStart() {
        FsTaskWork fsTaskWork = getTaskWork();

        FsTaskSett fsTaskSett = getTaskSett();

        if (!WorkFlowConstant.WF_STATUS_RUNNING.equals(fsTaskSett.getTaskStatus())) {
            throw new FsServiceException("清算工作编码需先经过申请:[" + taskDto.getTaskStage());
        }
        // 如果操作员还没有请求工作开始, 或者工作已处于"暂停"或"取消"或"完成"状态, 就不会有阶段,更不会有任务的开始了
        if (!WorkFlowConstant.WF_STATUS_RUNNING.equals(fsTaskWork.getTaskWorkStatus())) {
            throw new FsServiceException("工作流程没有处于运行中, 不能开始清算任务" + taskDto.getTaskJobSn());
        }
        // 开启任务
        FsTaskDetail stkSettDetail = fsTaskDetailAtom.selectByKey(taskDto.getTaskDate(), taskDto.getTaskJobSn(), taskDto.getTaskStage(), taskDto.getTasks());
        if (stkSettDetail == null) {
            FsTask fsTask = fsTaskAtom.selectByPrimaryKey(taskDto.getTasks());
            if (fsTask == null) {
                throw new FsServiceException("工作单元还未开始" + taskDto.getTasks());
            }
            FsTaskDetail taskDetail = new FsTaskDetail();
            taskDetail.setFsTask(taskDto.getTasks());
            taskDetail.setTaskSchedule(0);
            taskDetail.setTaskDate(taskDto.getTaskDate());
            taskDetail.setTaskWorkSn(taskDto.getTaskJobSn());
            taskDetail.setFsTaskName(fsTask.getTasksName());
            taskDetail.setTaskStatus("1");
            taskDetail.setTaskContent(" ");
            taskDetail.setErrCode(0);
            taskDetail.setErrMsg(" ");
            taskDetail.setBgnTime(DateUtils.getTimestamp());
            taskDetail.setEndTime(DateUtils.getDefualtTime());
            int count = fsTaskDetailAtom.insert(taskDetail);
            if (count < 1) {
                throw new FsServiceException("工作任务处理出错， 保存fsTaskDetail数据失败!");
            }
        } else {
            FsTaskDetail fsTaskDetail = new FsTaskDetail();
            fsTaskDetail.setTaskDate(taskDto.getTaskDate());
            fsTaskDetail.setTaskWorkSn(taskDto.getTaskJobSn());
            fsTaskDetail.setTaskStage(taskDto.getTaskStage());
            fsTaskDetail.setFsTask(taskDto.getTasks());
            fsTaskDetail.setTaskStatus(WorkFlowConstant.WF_STATUS_RUNNING);
            fsTaskDetail.setTaskContent(" ");
            fsTaskDetail.setEndTime(DateUtils.getDefualtTime());
            int count = fsTaskDetailAtom.update(fsTaskDetail);
            if (count < 1) {
                throw new FsServiceException("工作明细记录出现一致性错误");
            }
        }
    }

    public void settTaskEnd() {

        FsTaskDetail taskDetail = fsTaskDetailAtom.selectByKey(taskDto.getTaskDate(), taskDto.getTaskJobSn(), taskDto.getTaskStage(), taskDto.getTasks());
        if (taskDetail == null) {
            throw new FsServiceException("工作流程未开始" + taskDto.getTasks());
        }

        // 如果操作员还没有申请开始此清算任务, 或者清算任务已处于"失败"或"完成"状态, 任务用不着结束
        if (!WorkFlowConstant.WF_STATUS_RUNNING.equals(taskDetail.getTaskStatus())) {
            throw new FsServiceException("工作流程还未申请:{" + taskDetail.getTaskWorkSn() + "}");
        }

        FsTaskDetail fsTaskDetail = new FsTaskDetail();
        fsTaskDetail.setTaskDate(taskDto.getTaskDate());
        fsTaskDetail.setTaskWorkSn(taskDto.getTaskJobSn());
        fsTaskDetail.setTaskStage(taskDto.getTaskStage());
        fsTaskDetail.setFsTask(taskDto.getTasks());
        fsTaskDetail.setTaskStatus(taskDto.getTaskStatus());
        String context = taskDto.getTaskContent() == null ? " " : taskDto.getTaskContent();
        fsTaskDetail.setTaskContent(context);
        fsTaskDetail.setEndTime(DateUtils.getDefualtTime());
        int count = fsTaskDetailAtom.update(fsTaskDetail);
        if (count < 1) {
            throw new FsServiceException("工作明细记录出现一致性错误");
        }

    }

}
