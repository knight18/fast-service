package com.x.fs.workflow.server.inter;

public interface IWorkFlowLogger {

    void setFirstResult(String msgCode, String msg);

    /**
     * 第二结果集设置，setSecondResultBegin, addRow,setColValue,setColValue,saveRow,setSecondResultEnd是配套使用的。
     *
     * @param colNum
     * @param colNames
     */
    void setSecondResultBegin(Integer colNum, String colNames);

    void addRow();

    void setColValue(String name, String value);

    void saveRow();

    void setSecondResultEnd();

    Long getCurrentWuLogSn();

}