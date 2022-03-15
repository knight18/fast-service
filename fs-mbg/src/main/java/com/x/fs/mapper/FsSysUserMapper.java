package com.x.fs.mapper;

import com.x.fs.model.FsSysUser;
import com.x.fs.model.FsSysUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FsSysUserMapper {
    long countByExample(FsSysUserExample example);

    int deleteByExample(FsSysUserExample example);

    int deleteByPrimaryKey(String userCode);

    int insert(FsSysUser record);

    int insertSelective(FsSysUser record);

    List<FsSysUser> selectByExample(FsSysUserExample example);

    FsSysUser selectByPrimaryKey(String userCode);

    int updateByExampleSelective(@Param("record") FsSysUser record, @Param("example") FsSysUserExample example);

    int updateByExample(@Param("record") FsSysUser record, @Param("example") FsSysUserExample example);

    int updateByPrimaryKeySelective(FsSysUser record);

    int updateByPrimaryKey(FsSysUser record);
}