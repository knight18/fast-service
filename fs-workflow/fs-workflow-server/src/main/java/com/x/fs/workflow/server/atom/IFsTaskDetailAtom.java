package com.x.fs.workflow.server.atom;

import com.x.fs.mbg.model.FsTaskDetail;

/**
 * @author x
 */
public interface IFsTaskDetailAtom {

    int insert(FsTaskDetail fsTaskDetail);

    FsTaskDetail selectByKey(Integer taskDate, Integer taskWorkSn, Integer taskStage, Integer fsTask);

   int deleteByKey(Integer taskDate, Integer taskWorkSn, Integer taskStage, Integer fsTask);

    int update(FsTaskDetail fsTaskDetail);



}
