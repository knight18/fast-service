package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsTask;
import com.x.fs.mbg.model.FsTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsTaskMapper {
    long countByExample(FsTaskExample example);

    int deleteByExample(FsTaskExample example);

    int deleteByPrimaryKey(Integer tasks);

    int insert(FsTask record);

    int insertSelective(FsTask record);

    List<FsTask> selectByExample(FsTaskExample example);

    FsTask selectByPrimaryKey(Integer tasks);

    int updateByExampleSelective(@Param("record") FsTask record, @Param("example") FsTaskExample example);

    int updateByExample(@Param("record") FsTask record, @Param("example") FsTaskExample example);

    int updateByPrimaryKeySelective(FsTask record);

    int updateByPrimaryKey(FsTask record);
}