package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsTaskSett;
import com.x.fs.mbg.model.FsTaskSettExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsTaskSettMapper {
    long countByExample(FsTaskSettExample example);

    int deleteByExample(FsTaskSettExample example);

    int deleteByPrimaryKey(@Param("taskDate") Integer taskDate, @Param("taskWorkSn") Integer taskWorkSn, @Param("taskPhase") Integer taskPhase);

    int insert(FsTaskSett record);

    int insertSelective(FsTaskSett record);

    List<FsTaskSett> selectByExample(FsTaskSettExample example);

    FsTaskSett selectByPrimaryKey(@Param("taskDate") Integer taskDate, @Param("taskWorkSn") Integer taskWorkSn, @Param("taskPhase") Integer taskPhase);

    int updateByExampleSelective(@Param("record") FsTaskSett record, @Param("example") FsTaskSettExample example);

    int updateByExample(@Param("record") FsTaskSett record, @Param("example") FsTaskSettExample example);

    int updateByPrimaryKeySelective(FsTaskSett record);

    int updateByPrimaryKey(FsTaskSett record);
}