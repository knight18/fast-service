package com.x.fs.database.server.atom.impl;

import com.x.fs.database.server.atom.DatabaseBackupInfoAtom;
import com.x.fs.mbg.mapper.DatabaseBackupInfoMapper;
import com.x.fs.mbg.model.DatabaseBackupInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseBackupInfoAtomImpl implements DatabaseBackupInfoAtom {

    @Autowired
    private DatabaseBackupInfoMapper databaseBackupInfoMapper;

    @Override
    public DatabaseBackupInfo getDataBaseInfoByKey(String databaseName,String databaseId,String databasePort){
        return databaseBackupInfoMapper.selectByPrimaryKey(databaseName,databaseId,databasePort);
    }






}
