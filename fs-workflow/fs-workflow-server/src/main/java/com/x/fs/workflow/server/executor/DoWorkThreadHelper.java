package com.x.fs.workflow.server.executor;

import com.x.fs.base.dto.ContextDTO;
import com.x.fs.workflow.server.inter.IDoWorkThreadHelper;
import com.x.fs.workflow.server.inter.IWorkFlowLogger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author x
 */
public class DoWorkThreadHelper implements IDoWorkThreadHelper {

    private AtomicBoolean exitLoopCount;
    private WorkFlowLogger wuLogger;
    private AtomicInteger wuLeaseCount;
    private ContextDTO contextDTO;

    public DoWorkThreadHelper(){
        this.exitLoopCount = new AtomicBoolean(false);
        this.wuLeaseCount = new AtomicInteger(0);
    }

    public void setWuLogger(WorkFlowLogger wuLogger) {
        this.wuLogger = wuLogger;
    }

    public void setExitLoop() {
        this.exitLoopCount.set(true);
    }


    @Override
    public boolean continueLoop() {
        return !this.exitLoopCount.get();
    }

    @Override
    public String getWfGuid() {
        return this.wuLogger.getWfGuid();
    }

    @Override
    public IWorkFlowLogger getWorkFlowLogger() {
        return this.wuLogger;
    }

    @Override
    public boolean dbFence() {
        return this.wuLeaseCount.get() != -1;
    }

    @Override
    public ContextDTO getContext() {
        return this.contextDTO = contextDTO;
    }

    public void setContext(ContextDTO contextDTO) {
        this.contextDTO = contextDTO;
    }

    public void setWuLeaseCount(int wuLeaseCount) {
        this.wuLeaseCount.set(wuLeaseCount);
    }

    public void release() {
        if (this.wuLogger == null) {
            return;
        }
        this.wuLogger.release();
    }
}
