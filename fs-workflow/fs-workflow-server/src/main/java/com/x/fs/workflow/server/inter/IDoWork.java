package com.x.fs.workflow.server.inter;

/**
 * @author x
 */
public interface IDoWork {

    /**
     * 实现这个接口
     * @param param
     * @param workThreadHelper
     * @return
     */
    int doWork(String param, IDoWorkThreadHelper workThreadHelper);

}
