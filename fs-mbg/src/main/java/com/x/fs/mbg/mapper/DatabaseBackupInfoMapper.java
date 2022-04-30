package com.x.fs.mbg.mapper;

import com.x.fs.mbg.model.DatabaseBackupInfo;
import com.x.fs.mbg.model.DatabaseBackupInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatabaseBackupInfoMapper {
    long countByExample(DatabaseBackupInfoExample example);

    int deleteByExample(DatabaseBackupInfoExample example);

    int deleteByPrimaryKey(@Param("databaseName") String databaseName, @Param("databaseIp") String databaseIp, @Param("databasePort") String databasePort);

    int insert(DatabaseBackupInfo record);

    int insertSelective(DatabaseBackupInfo record);

    List<DatabaseBackupInfo> selectByExample(DatabaseBackupInfoExample example);

    DatabaseBackupInfo selectByPrimaryKey(@Param("databaseName") String databaseName, @Param("databaseIp") String databaseIp, @Param("databasePort") String databasePort);

    int updateByExampleSelective(@Param("record") DatabaseBackupInfo record, @Param("example") DatabaseBackupInfoExample example);

    int updateByExample(@Param("record") DatabaseBackupInfo record, @Param("example") DatabaseBackupInfoExample example);

    int updateByPrimaryKeySelective(DatabaseBackupInfo record);

    int updateByPrimaryKey(DatabaseBackupInfo record);
}