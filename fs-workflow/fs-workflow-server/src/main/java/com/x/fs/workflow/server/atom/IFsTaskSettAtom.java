package com.x.fs.workflow.server.atom;

import com.x.fs.mbg.model.FsTaskSett;

/**
 *
 * @author x
 */
public interface IFsTaskSettAtom {

    int insert(FsTaskSett fsTaskSett);

    FsTaskSett selectByKey(Integer taskDate, Integer taskWorkSn, Integer taskPhase);



}
