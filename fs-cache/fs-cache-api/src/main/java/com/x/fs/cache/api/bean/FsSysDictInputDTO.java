package com.x.fs.cache.api.bean;

import java.io.Serializable;

/**
 * @author x
 */
public class FsSysDictInputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dictNo;

    private String key;

    public String getDictNo() {
        return dictNo;
    }

    public void setDictNo(String dictNo) {
        this.dictNo = dictNo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "FsSysDictInputDTO{" +
                "dictNo='" + dictNo + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
