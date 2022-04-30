package com.x.fs.database.server.service;

import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.database.api.bean.RegistryInfoDTO;

/**
 * @author x
 */
public interface MysqlDataBaseService {

    void mySqlDataBaseBackUp(DataBaseBackUpDTO inputParam);

    int insertRegistryInfo(RegistryInfoDTO input);
}
