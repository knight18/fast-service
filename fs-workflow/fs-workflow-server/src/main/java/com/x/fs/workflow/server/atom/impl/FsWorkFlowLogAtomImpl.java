package com.x.fs.workflow.server.atom.impl;

import com.x.fs.mbg.model.FsWorkFlowLog;
import com.x.fs.mbg.model.FsWorkFlowLogExample;
import com.x.fs.workflow.server.atom.FsWorkFlowLogAtom;
import com.x.fs.workflow.server.dao.FsWorkFlowLogMapperExt;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author x
 */
@Component
public class FsWorkFlowLogAtomImpl implements FsWorkFlowLogAtom {

    @Autowired
    private FsWorkFlowLogMapperExt fsWorkFlowLogMapperExt;


    @Override
    public int saveWorkFlowLog(Long wfLogId, String wfLogText, String wfGuid, Date time) {
        FsWorkFlowLog fsWorkFlowLog = new FsWorkFlowLog();
        fsWorkFlowLog.setWfLogId(wfLogId);
        fsWorkFlowLog.setWfLogText(wfLogText);
        fsWorkFlowLog.setWfGuid(wfGuid);
        fsWorkFlowLog.setWfTime(time);
        return fsWorkFlowLogMapperExt.insert(fsWorkFlowLog);
    }

    @Override
    public List<FsWorkFlowLog> selectWorkFlowLogByUid(String wfGuid) {
        FsWorkFlowLogExample example = new FsWorkFlowLogExample();
        example.setOrderByClause("WF_GUID,WF_LOG_ID");
        FsWorkFlowLogExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(wfGuid)){
            criteria.andWfGuidEqualTo(wfGuid);
        }
        return fsWorkFlowLogMapperExt.selectByExample(example);
    }
}
