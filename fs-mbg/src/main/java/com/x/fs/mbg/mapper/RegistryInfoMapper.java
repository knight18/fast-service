package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.RegistryInfo;
import com.x.fs.mbg.model.RegistryInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface RegistryInfoMapper {
    long countByExample(RegistryInfoExample example);

    int deleteByExample(RegistryInfoExample example);

    int deleteByPrimaryKey(String registryId);

    int insert(RegistryInfo record);

    int insertSelective(RegistryInfo record);

    List<RegistryInfo> selectByExample(RegistryInfoExample example);

    RegistryInfo selectByPrimaryKey(String registryId);

    int updateByExampleSelective(@Param("record") RegistryInfo record, @Param("example") RegistryInfoExample example);

    int updateByExample(@Param("record") RegistryInfo record, @Param("example") RegistryInfoExample example);

    int updateByPrimaryKeySelective(RegistryInfo record);

    int updateByPrimaryKey(RegistryInfo record);
}