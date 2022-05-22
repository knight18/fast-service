package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsWorkFlowTracker;
import com.x.fs.mbg.model.FsWorkFlowTrackerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsWorkFlowTrackerMapper {
    long countByExample(FsWorkFlowTrackerExample example);

    int deleteByExample(FsWorkFlowTrackerExample example);

    int deleteByPrimaryKey(String wfGuid);

    int insert(FsWorkFlowTracker record);

    int insertSelective(FsWorkFlowTracker record);

    List<FsWorkFlowTracker> selectByExample(FsWorkFlowTrackerExample example);

    FsWorkFlowTracker selectByPrimaryKey(String wfGuid);

    int updateByExampleSelective(@Param("record") FsWorkFlowTracker record, @Param("example") FsWorkFlowTrackerExample example);

    int updateByExample(@Param("record") FsWorkFlowTracker record, @Param("example") FsWorkFlowTrackerExample example);

    int updateByPrimaryKeySelective(FsWorkFlowTracker record);

    int updateByPrimaryKey(FsWorkFlowTracker record);
}