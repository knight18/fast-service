package com.x.fs.database.api.bean;

import java.io.Serializable;
import java.util.Date;

public class RegistryInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String registryId;

    private String regkeyName;

    private String regkeyValue;

    private String regkeyStatus;

    private String valueDesc;

    private Date creationTime;

    private Date uptTime;

    public String getRegistryId() {
        return registryId;
    }

    public void setRegistryId(String registryId) {
        this.registryId = registryId;
    }

    public String getRegkeyName() {
        return regkeyName;
    }

    public void setRegkeyName(String regkeyName) {
        this.regkeyName = regkeyName;
    }

    public String getRegkeyValue() {
        return regkeyValue;
    }

    public void setRegkeyValue(String regkeyValue) {
        this.regkeyValue = regkeyValue;
    }

    public String getRegkeyStatus() {
        return regkeyStatus;
    }

    public void setRegkeyStatus(String regkeyStatus) {
        this.regkeyStatus = regkeyStatus;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
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
        sb.append(", registryId=").append(registryId);
        sb.append(", regkeyName=").append(regkeyName);
        sb.append(", regkeyValue=").append(regkeyValue);
        sb.append(", regkeyStatus=").append(regkeyStatus);
        sb.append(", valueDesc=").append(valueDesc);
        sb.append(", creationTime=").append(creationTime);
        sb.append(", uptTime=").append(uptTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
