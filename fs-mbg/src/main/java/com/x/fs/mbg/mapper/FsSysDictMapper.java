package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.FsSysDict;
import com.x.fs.mbg.model.FsSysDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FsSysDictMapper {
    long countByExample(FsSysDictExample example);

    int deleteByExample(FsSysDictExample example);

    int deleteByPrimaryKey(@Param("dictNo") String dictNo, @Param("dictKey") String dictKey);

    int insert(FsSysDict record);

    int insertSelective(FsSysDict record);

    List<FsSysDict> selectByExample(FsSysDictExample example);

    FsSysDict selectByPrimaryKey(@Param("dictNo") String dictNo, @Param("dictKey") String dictKey);

    int updateByExampleSelective(@Param("record") FsSysDict record, @Param("example") FsSysDictExample example);

    int updateByExample(@Param("record") FsSysDict record, @Param("example") FsSysDictExample example);

    int updateByPrimaryKeySelective(FsSysDict record);

    int updateByPrimaryKey(FsSysDict record);
}