package com.x.fs.workflow.server.atom;

import com.x.fs.mbg.model.FsTaskWork;

import java.util.List;

public interface IFsTaskWorkAtom {

    int insert(FsTaskWork record);

    List<FsTaskWork> selectByExample(FsTaskWork fsTaskWork);

    FsTaskWork selectByPrimaryKey( Integer taskDate,  Integer taskWorkSn);


}
