package com.x.fs.workflow.server.atom.impl;

import com.x.fs.common.exception.FsServiceException;
import com.x.fs.mbg.mapper.FsTaskSettMapper;
import com.x.fs.mbg.model.FsTaskSett;
import com.x.fs.workflow.server.atom.IFsTaskSettAtom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author x
 */
@Component
public class FsTaskSettAtomImpl implements IFsTaskSettAtom {

    @Autowired
    private FsTaskSettMapper fsTaskSettMapper;


    @Override
    public int insert(FsTaskSett fsTaskSett) {
        return fsTaskSettMapper.insert(fsTaskSett);
    }

    @Override
    public FsTaskSett selectByKey(Integer taskDate, Integer taskWorkSn, Integer taskPhase) {
        if (taskDate == null || taskWorkSn == null || taskPhase == null) {
            throw new FsServiceException("入参字段taskDate,taskWorkSn,taskPhase不能为null,taskPhase:[" + taskPhase + "],taskWorkSn[" + taskWorkSn + "],taskPhase[" + taskPhase + "]");
        }
        return fsTaskSettMapper.selectByPrimaryKey(taskDate, taskWorkSn, taskPhase);
    }


}
