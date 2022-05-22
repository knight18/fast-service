package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsWorkFlowLog;
import com.x.fs.mbg.model.FsWorkFlowLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsWorkFlowLogMapper {
    long countByExample(FsWorkFlowLogExample example);

    int deleteByExample(FsWorkFlowLogExample example);

    int deleteByPrimaryKey(@Param("wfLogId") Long wfLogId, @Param("wfGuid") String wfGuid);

    int insert(FsWorkFlowLog record);

    int insertSelective(FsWorkFlowLog record);

    List<FsWorkFlowLog> selectByExample(FsWorkFlowLogExample example);

    FsWorkFlowLog selectByPrimaryKey(@Param("wfLogId") Long wfLogId, @Param("wfGuid") String wfGuid);

    int updateByExampleSelective(@Param("record") FsWorkFlowLog record, @Param("example") FsWorkFlowLogExample example);

    int updateByExample(@Param("record") FsWorkFlowLog record, @Param("example") FsWorkFlowLogExample example);

    int updateByPrimaryKeySelective(FsWorkFlowLog record);

    int updateByPrimaryKey(FsWorkFlowLog record);
}