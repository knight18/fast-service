package com.x.fs.workflow.server.atom.impl;

import com.x.fs.mbg.model.FsWorkFlowTracker;
import com.x.fs.mbg.model.FsWorkFlowTrackerExample;
import com.x.fs.workflow.server.atom.FsWorkFlowTrackerAtom;
import com.x.fs.workflow.server.dao.FsWorkFlowTrackerMapperExt;
import com.x.fs.workflow.server.dto.WorkFlowTrackerOneDto;
import com.x.fs.workflow.server.dto.WorkFlowTrackerTwoDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author x
 */
@Component
public class FsWorkFlowTrackerAtomImpl implements FsWorkFlowTrackerAtom {

    @Autowired
    private FsWorkFlowTrackerMapperExt fsWorkFlowTrackerMapperExt;


    @Override
    public int saveWorkFlowTracker(FsWorkFlowTracker fsWorkFlowTracker) {
        return fsWorkFlowTrackerMapperExt.insert(fsWorkFlowTracker);
    }

    @Override
    public int updateWfStatusByUid(String wfGuid, String wfStatus) {
        return fsWorkFlowTrackerMapperExt.updateWfStatusByUid(wfGuid, wfStatus);
    }

    @Override
    public int updateWfSignalByid(String wfGuid, Integer wfSignal, Integer wfLeaseCount) {
        return fsWorkFlowTrackerMapperExt.updateWfSignalByid(wfGuid, wfSignal, wfLeaseCount);
    }

    /**
     * 查询所有未结束工作流程信息
     *
     * @return
     */
    @Override
    public List<WorkFlowTrackerOneDto> selectUnFinishWorkFlow() {
        return fsWorkFlowTrackerMapperExt.selectUnFinishWorkFlow();
    }

    /**
     * 查询工作流程状态信息
     *
     * @param wfGuids 多个值，用逗号隔开
     * @return
     */
    @Override
    public List<WorkFlowTrackerTwoDto> selectWfStatusByWfGuides(String wfGuids) {
        return fsWorkFlowTrackerMapperExt.selectWfStatusByWfGuides(wfGuids);
    }

    @Override
    public int updateSignalByUid(int signal, Integer leaseCount, String wfGuid) {
        return fsWorkFlowTrackerMapperExt.updateSignalByUid(signal,leaseCount,wfGuid);
    }

    @Override
    public List<FsWorkFlowTracker> selectWorkFlowTrackerByUid(String wfGuid,String status) {
        FsWorkFlowTrackerExample example = new FsWorkFlowTrackerExample();
        FsWorkFlowTrackerExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(wfGuid)){
            criteria.andWfGuidEqualTo(wfGuid);
        }
        if(StringUtils.isNotBlank(status)){
            criteria.andWfStatusEqualTo(status);
        }
        return fsWorkFlowTrackerMapperExt.selectByExample(example);
    }


}
