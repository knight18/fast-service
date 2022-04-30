package com.x.fs.database.api.service;

import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.database.api.bean.RegistryInfoDTO;

/**
 * 数据库API
 * @author x
 */
public interface DataBaseApiService {

    /**
     * Mysql数据库备份接口
     * @param dataBaseBackUpDTO
     */
    void mySqlDataBaseBackUp(DataBaseBackUpDTO dataBaseBackUpDTO);



    int insertRegistryInfo(RegistryInfoDTO input);


}
