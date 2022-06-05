package com.x.fs.workflow.server.atom.impl;

import com.x.fs.mbg.mapper.FsTaskMapper;
import com.x.fs.mbg.model.FsTask;
import com.x.fs.workflow.server.atom.IFsTaskAtom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author x
 */
@Component
public class FsTaskAtomImpl implements IFsTaskAtom {

    @Autowired
    private FsTaskMapper fsTaskMapper;


    @Override
    public int insert(FsTask fsTask) {
        return fsTaskMapper.insert(fsTask);
    }

    @Override
    public int deleteByPrimaryKey(Integer tasks) {
        return fsTaskMapper.deleteByPrimaryKey(tasks);
    }

    @Override
    public FsTask selectByPrimaryKey(Integer tasks) {
        return fsTaskMapper.selectByPrimaryKey(tasks);
    }
}
