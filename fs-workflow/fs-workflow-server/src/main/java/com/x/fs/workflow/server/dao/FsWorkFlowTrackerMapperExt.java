package com.x.fs.workflow.server.dao;

import com.x.fs.mbg.mapper.FsWorkFlowTrackerMapper;
import com.x.fs.mbg.model.FsWorkFlowTracker;
import com.x.fs.workflow.server.dto.WorkFlowTrackerOneDto;
import com.x.fs.workflow.server.dto.WorkFlowTrackerTwoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author x
 */
public interface FsWorkFlowTrackerMapperExt extends FsWorkFlowTrackerMapper {


    int updateWfStatusByUid(@Param("wfGuid") String wfGuid,@Param("wfStatus") String wfStatus);


    int updateWfSignalByid(@Param("wfGuid")String wfGuid, @Param("wfSignal")Integer wfSignal, @Param("wfLeaseCount")Integer wfLeaseCount);

    List<WorkFlowTrackerOneDto> selectUnFinishWorkFlow();

    List<WorkFlowTrackerTwoDto> selectWfStatusByWfGuides(@Param("wfGuids")String wfGuids);

    int updateSignalByUid(@Param("signal")int signal, @Param("leaseCount")Integer leaseCount, @Param("wfGuid")String wfGuid);
}
