package com.x.fs.workflow.server.atom;

import com.x.fs.mbg.model.FsWorkFlowLog;

import java.util.Date;
import java.util.List;

/**
 * @author x
 */
public interface FsWorkFlowLogAtom {

    int saveWorkFlowLog(Long wfLogId, String wfLogText, String wfGuid, Date time);

    List<FsWorkFlowLog> selectWorkFlowLogByUid(String wfGuid);
}
