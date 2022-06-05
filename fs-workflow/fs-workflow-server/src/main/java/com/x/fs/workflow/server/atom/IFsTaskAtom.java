package com.x.fs.workflow.server.atom;

import com.x.fs.mbg.model.FsTask;

public interface IFsTaskAtom {

    int insert(FsTask fsTask);


    int deleteByPrimaryKey(Integer tasks);

    FsTask selectByPrimaryKey(Integer tasks);


}
