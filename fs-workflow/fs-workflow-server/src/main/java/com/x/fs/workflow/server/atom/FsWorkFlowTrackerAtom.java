package com.x.fs.workflow.server.atom;

import com.x.fs.mbg.model.FsWorkFlowTracker;
import com.x.fs.workflow.server.dto.WorkFlowTrackerOneDto;
import com.x.fs.workflow.server.dto.WorkFlowTrackerTwoDto;

import java.util.List;

/**
 * @author x
 */

public interface FsWorkFlowTrackerAtom {


    int saveWorkFlowTracker(FsWorkFlowTracker fsWorkFlowTracker);

    /**
     * 更新工作流程状态
     *
     * @param wfGuid
     * @param wfStatus
     * @return
     */
    int updateWfStatusByUid(String wfGuid, String wfStatus);

    /**
     * 更新流程中的信号
     *
     * @param wfGuid
     * @param wuSignal
     * @param wuLeaseCount
     * @return
     */
    int updateWfSignalByid(String wfGuid, Integer wuSignal, Integer wuLeaseCount);


    /**
     * 查询所有未结束工作单元信息
     *
     * @return
     */
    List<WorkFlowTrackerOneDto> selectUnFinishWorkFlow();


    /**
     * 查询工作单元状态信息
     * @param wfGuids  多个值，用逗号隔开
     * @return
     */
    List<WorkFlowTrackerTwoDto> selectWfStatusByWfGuides(String wfGuids);

    int updateSignalByUid(int signal,Integer leaseCount,String wfGuid);


    List<FsWorkFlowTracker> selectWorkFlowTrackerByUid(String wfGuid,String status);

}
