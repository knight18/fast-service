package com.x.fs.workflow.server.atom.impl;

import com.x.fs.mbg.mapper.FsTaskDetailMapper;
import com.x.fs.mbg.model.FsTaskDetail;
import com.x.fs.mbg.model.FsTaskDetailExample;
import com.x.fs.workflow.server.atom.IFsTaskDetailAtom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FsTaskDetailAtomImpl implements IFsTaskDetailAtom {

    @Autowired
    private FsTaskDetailMapper fsTaskDetailMapper;

    @Override
    public int insert(FsTaskDetail fsTaskDetail) {
        return fsTaskDetailMapper.insert(fsTaskDetail);
    }

    @Override
    public FsTaskDetail selectByKey(Integer taskDate, Integer taskWorkSn, Integer taskStage, Integer fsTask) {
        return fsTaskDetailMapper.selectByPrimaryKey(taskDate, taskWorkSn, taskStage, fsTask);
    }

    @Override
    public int deleteByKey(Integer taskDate, Integer taskWorkSn, Integer taskStage, Integer fsTask) {
        return fsTaskDetailMapper.deleteByPrimaryKey(taskDate, taskWorkSn, taskStage, fsTask);
    }

    @Override
    public int update(FsTaskDetail fsTaskDetail) {
        FsTaskDetail record = new FsTaskDetail();
        if (fsTaskDetail.getBgnTime() != null) {
            record.setBgnTime(fsTaskDetail.getBgnTime());
        }
        if (fsTaskDetail.getEndTime() != null) {
            record.setEndTime(fsTaskDetail.getEndTime());
        }
        if (!StringUtils.isEmpty(fsTaskDetail.getTaskStatus())) {
            record.setTaskStatus(fsTaskDetail.getTaskStatus());
        }
        if (!StringUtils.isEmpty(fsTaskDetail.getTaskContent())) {
            record.setTaskContent(fsTaskDetail.getTaskContent());
        }

        FsTaskDetailExample example = new FsTaskDetailExample();
        FsTaskDetailExample.Criteria criteria = example.createCriteria();
        criteria.andTaskDateEqualTo(fsTaskDetail.getTaskDate());
        criteria.andTaskWorkSnEqualTo(fsTaskDetail.getTaskWorkSn());
        criteria.andTaskStageEqualTo(fsTaskDetail.getTaskStage());
        criteria.andFsTaskEqualTo(fsTaskDetail.getFsTask());
        return fsTaskDetailMapper.updateByExample(record, example);
    }


}
