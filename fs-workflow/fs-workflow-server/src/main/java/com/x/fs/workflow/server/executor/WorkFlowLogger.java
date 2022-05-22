package com.x.fs.workflow.server.executor;

import com.google.gson.JsonObject;
import com.x.fs.base.transaction.FsTransactionManager;
import com.x.fs.base.utils.DateUtils;
import com.x.fs.base.utils.StringUtils;
import com.x.fs.workflow.server.atom.FsWorkFlowLogAtom;
import com.x.fs.workflow.server.inter.IWorkFlowLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author x
 */
@Slf4j
public class WorkFlowLogger implements IWorkFlowLogger {

    @Autowired
    private FsWorkFlowLogAtom fsWorkFlowLogAtom;

    private final String wfGuid;

    private final String defaultDataSourceId;

    private final Map<Integer, JsonObject> secondResult;

    private JsonObject row;
    private Integer rowID;
    private AtomicLong wfLogId = new AtomicLong(0);

    public WorkFlowLogger(String wfGuid, String defaultDataSourceId) {
        this.wfGuid = wfGuid;
        this.secondResult = new HashMap<>();
        this.wfLogId.set(1);
        this.defaultDataSourceId = defaultDataSourceId;
    }


    @Override
    public void setFirstResult(String msgCode, String msg) {
        JsonObject errMsg = new JsonObject();
        errMsg.addProperty("MSG_CODE", msgCode);
        errMsg.addProperty("MSG_INFO", StringUtils.subStringByByte(msg, 1950));
        JsonObject js = new JsonObject();
        js.add("FIRST_RESULT", errMsg);
        this.writeWorkFlowLog(js.toString());
    }

    /**
     * 第二结果集设置，setSecondResultBegin, addRow,setColValue,setColValue,saveRow,setSecondResultEnd是配套使用的。
     *
     * @param colNum
     * @param colNames
     */
    @Override
    public void setSecondResultBegin(Integer colNum, String colNames) {
        this.secondResult.clear();
        this.rowID = 0;
    }

    @Override
    public void addRow() {
        this.row = new JsonObject();
        this.rowID++;
    }

    @Override
    public void setColValue(String name, String value) {
        this.row.addProperty(name, value);
    }

    @Override
    public void saveRow() {
        if (this.row == null) {
            return;
        }
        this.secondResult.put(this.rowID, this.row);
        this.row = null;
    }

    @Override
    public void setSecondResultEnd() {
        JsonObject json = new JsonObject();
        JsonObject jsonObject = new JsonObject();
        for (Integer key : this.secondResult.keySet()) {
            jsonObject.add(key.toString(), this.secondResult.get(key));
        }
        json.add("SECOND_RESULT", jsonObject);
        this.writeWorkFlowLog(json.toString());
        this.rowID = 0;
    }

    @Override
    public Long getCurrentWuLogId() {
        return wfLogId.get();
    }


    public void writeWorkFlowLog(String logText) {
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager(this.defaultDataSourceId, Propagation.REQUIRES_NEW, Isolation.DEFAULT)) {
            fsWorkFlowLogAtom.saveWorkFlowLog(this.wfLogId.getAndIncrement(), logText, this.getWfGuid(), DateUtils.getTimestamp());
            fsTransactionManager.doSuccess();
        } catch (Exception e) {
            log.error("save WorkFlowLog error! errMsg:{}.callstack:{}", e.getMessage(), e.getStackTrace());
        }
    }

    public String getWfGuid() {
        return this.wfGuid;
    }

    public void release() {
    }
}
