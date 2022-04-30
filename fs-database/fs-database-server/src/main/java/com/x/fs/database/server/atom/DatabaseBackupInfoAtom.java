package com.x.fs.database.server.atom;

import com.x.fs.mbg.model.DatabaseBackupInfo;

/**
 * @author x
 */
public interface DatabaseBackupInfoAtom {


    DatabaseBackupInfo getDataBaseInfoByKey(String databaseName, String databaseId, String databasePort);

}
