package com.x.fs.database.api.bean;

import java.io.Serializable;

/**
 * @author x
 */
public class DataBaseBackUpDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String databaseName;

    private String databaseId;

    private String databasePort;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    @Override
    public String toString() {
        return "DataBaseBackUpDTO{" +
                "databaseName='" + databaseName + '\'' +
                ", databaseId='" + databaseId + '\'' +
                ", databasePort='" + databasePort + '\'' +
                '}';
    }
}
