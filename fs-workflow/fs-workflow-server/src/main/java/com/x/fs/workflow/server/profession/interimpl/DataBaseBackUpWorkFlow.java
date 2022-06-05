package com.x.fs.workflow.server.profession.interimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.fs.base.transaction.FsDefaultTransaction;
import com.x.fs.base.utils.DataBaseConnectUtils;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.workflow.server.inter.IDoWork;
import com.x.fs.workflow.server.inter.IDoWorkThreadHelper;
import com.x.fs.workflow.server.inter.IWorkFlowLogger;
import com.x.fs.workflow.server.profession.feignservice.FsDataBaseService;
import com.x.fs.workflow.server.utils.WorkFlowLogSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataBaseBackUpWorkFlow")
public class DataBaseBackUpWorkFlow implements IDoWork, FsDefaultTransaction {

    @Autowired
    private FsDataBaseService fsDataBaseService;

    /**
     * @param param
     * @param workThreadHelper
     * @return
     */
    @Override
    public int doWork(String param, IDoWorkThreadHelper workThreadHelper) {
        IWorkFlowLogger logger = workThreadHelper.getWorkFlowLogger();
        logger.setFirstResult("0", "数据库备份开始");
        WorkFlowLogSchedule schedule = new WorkFlowLogSchedule(logger);
        schedule.start("数据库备份任务开始执行");
        String backupFileName = "";
        try {
            // 获取参数
            ObjectMapper objectMapper = new ObjectMapper();
            DataBaseBackUpDTO databaseBackupWebParam = objectMapper.readValue(param, DataBaseBackUpDTO.class);

            schedule.step(2, "0", "请求入参数据[" + databaseBackupWebParam + "]");

            if (DataBaseConnectUtils.isMySql()) {
                fsDataBaseService.mySqlDataBaseBackUp(databaseBackupWebParam);
            } else if (DataBaseConnectUtils.isOracle()) {

            } else {
                throw new FsServiceException("暂不支持此种数据库类型备份");
            }
            schedule.finish("数据库备份成功[" + backupFileName + "]");
        } catch (Exception e) {
            e.printStackTrace();
            schedule.error("-1", e.getMessage());
            return -1;
        }
        return 0;
    }
}
