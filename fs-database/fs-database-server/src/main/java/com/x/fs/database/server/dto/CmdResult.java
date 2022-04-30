package com.x.fs.database.server.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author x
 */
public class CmdResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean valid;
    private BigDecimal consTime;
    private long byteNum;

    @Override
    public String toString() {
        return "CmdResult{" +
                "valid=" + valid +
                ", consTime=" + consTime +
                ", byteNum=" + byteNum +
                '}';
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public BigDecimal getConsTime() {
        return consTime;
    }

    public void setConsTime(BigDecimal consTime) {
        this.consTime = consTime;
    }

    public long getByteNum() {
        return byteNum;
    }

    public void setByteNum(long byteNum) {
        this.byteNum = byteNum;
    }
}
