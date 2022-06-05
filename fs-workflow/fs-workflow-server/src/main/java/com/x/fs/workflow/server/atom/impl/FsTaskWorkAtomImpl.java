package com.x.fs.workflow.server.atom.impl;

import com.x.fs.mbg.mapper.FsTaskWorkMapper;
import com.x.fs.mbg.model.FsTaskWork;
import com.x.fs.workflow.server.atom.IFsTaskWorkAtom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FsTaskWorkAtomImpl implements IFsTaskWorkAtom {

    @Autowired
    private FsTaskWorkMapper fsTaskWorkMapper;


    @Override
    public int insert(FsTaskWork record) {
        return fsTaskWorkMapper.insert(record);
    }

    @Override
    public List<FsTaskWork> selectByExample(FsTaskWork fsTaskWork) {
        return null;
    }

    @Override
    public FsTaskWork selectByPrimaryKey(Integer taskDate, Integer taskWorkSn) {
        return fsTaskWorkMapper.selectByPrimaryKey(taskDate,taskWorkSn);
    }
}
