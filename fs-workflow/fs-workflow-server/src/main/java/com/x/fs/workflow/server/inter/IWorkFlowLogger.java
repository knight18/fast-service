package com.x.fs.workflow.server.inter;

/**
 * @author x
 */
public interface IWorkFlowLogger {

    void setFirstResult(String msgCode, String msg);

    /**
     * 第二结果集设置
     *
     * @param colNum
     * @param colNames
     */
    void setSecondResultBegin(Integer colNum, String colNames);

    void addRow();

    void setColValue(String name, String value);

    void saveRow();

    void setSecondResultEnd();

    Long getCurrentWuLogId();

}