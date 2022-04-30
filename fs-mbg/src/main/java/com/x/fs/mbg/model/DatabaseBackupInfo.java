package com.x.fs.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class DatabaseBackupInfo implements Serializable {
    private String databaseName;

    private String databaseIp;

    private String databasePort;

    private String databaseId;

    private String backupDir;

    private String backupName;

    private String backupFileSuffix;

    private Date createTime;

    private Date uptTime;

    private static final long serialVersionUID = 1L;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseIp() {
        return databaseIp;
    }

    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getBackupDir() {
        return backupDir;
    }

    public void setBackupDir(String backupDir) {
        this.backupDir = backupDir;
    }

    public String getBackupName() {
        return backupName;
    }

    public void setBackupName(String backupName) {
        this.backupName = backupName;
    }

    public String getBackupFileSuffix() {
        return backupFileSuffix;
    }

    public void setBackupFileSuffix(String backupFileSuffix) {
        this.backupFileSuffix = backupFileSuffix;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", databaseName=").append(databaseName);
        sb.append(", databaseIp=").append(databaseIp);
        sb.append(", databasePort=").append(databasePort);
        sb.append(", databaseId=").append(databaseId);
        sb.append(", backupDir=").append(backupDir);
        sb.append(", backupName=").append(backupName);
        sb.append(", backupFileSuffix=").append(backupFileSuffix);
        sb.append(", createTime=").append(createTime);
        sb.append(", uptTime=").append(uptTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}