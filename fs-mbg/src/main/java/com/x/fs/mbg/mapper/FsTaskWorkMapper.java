package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsTaskWork;
import com.x.fs.mbg.model.FsTaskWorkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsTaskWorkMapper {
    long countByExample(FsTaskWorkExample example);

    int deleteByExample(FsTaskWorkExample example);

    int deleteByPrimaryKey(@Param("taskDate") Integer taskDate, @Param("taskWorkSn") Integer taskWorkSn);

    int insert(FsTaskWork record);

    int insertSelective(FsTaskWork record);

    List<FsTaskWork> selectByExample(FsTaskWorkExample example);

    FsTaskWork selectByPrimaryKey(@Param("taskDate") Integer taskDate, @Param("taskWorkSn") Integer taskWorkSn);

    int updateByExampleSelective(@Param("record") FsTaskWork record, @Param("example") FsTaskWorkExample example);

    int updateByExample(@Param("record") FsTaskWork record, @Param("example") FsTaskWorkExample example);

    int updateByPrimaryKeySelective(FsTaskWork record);

    int updateByPrimaryKey(FsTaskWork record);
}