package com.x.fs.database.server.service.impl;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import com.x.fs.asynctask.server.service.AsyncTaskService;
import com.x.fs.base.constant.FsBaseConstant;
import com.x.fs.base.dto.ConnectInfoDTO;
import com.x.fs.base.transaction.FsTransactionManager;
import com.x.fs.base.utils.*;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.common.utils.UniqueId;
import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.database.api.bean.RegistryInfoDTO;
import com.x.fs.database.server.atom.DatabaseBackupInfoAtom;
import com.x.fs.database.server.atom.RegistryInfoAtom;
import com.x.fs.database.server.dto.CmdResult;
import com.x.fs.base.dto.SSHExecuteResult;
import com.x.fs.database.server.service.MysqlDataBaseService;
import com.x.fs.mbg.mapper.FsWorkFlowLogMapper;
import com.x.fs.mbg.model.DatabaseBackupInfo;
import com.x.fs.mbg.model.FsWorkFlowLog;
import com.x.fs.mbg.model.RegistryInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.RejectedExecutionException;

/**
 * Mysql数据库备份
 * @author x
 */
@Slf4j
@Service
public class MysqlDataBaseServiceImpl implements MysqlDataBaseService {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Autowired
    private RegistryInfoAtom registryInfoAtom;

    @Autowired
    private DatabaseBackupInfoAtom databaseBackupInfoAtom;

    @Autowired
    private FsWorkFlowLogMapper fsWorkFlowLogMapper;

    @Override
    public int insertRegistryInfo(RegistryInfoDTO input) {

        long startTime = MilliSecondUtils.currentTimeMillis();
        Date date = DateUtils.getTimestamp();
        log.info("开始时间："+startTime);
//        for (long i =0; i < 5000000 ; i++){
//            long midTime = MilliSecondUtils.currentTimeMillis();
//            RegistryInfo info = new RegistryInfo();
//            info.setRegistryId(UniqueId.getInstance().nextId().toString());
//            info.setRegkeyName("测试"+i);
//            info.setRegkeyStatus("0");
//            info.setRegkeyValue("cs");
//            info.setValueDesc("测试");
//            info.setCreationTime(date);
//            info.setUptTime(date);
//            registryInfoAtom.insertRegistryInfo(info);
//            long time = MilliSecondUtils.currentTimeMillis();
//            log.info("第["+i+"]笔时间："+time+"一共需要："+ (time-midTime));
//        }

        try (FsTransactionManager fsTransactionManager = new FsTransactionManager()) {
            RegistryInfo info = new RegistryInfo();
            info.setRegistryId(UniqueId.getInstance().nextId().toString());
            info.setRegkeyName("测试");
            info.setRegkeyStatus("0");
            info.setRegkeyValue("cs");
            info.setValueDesc("测试");
            info.setCreationTime(date);
            info.setUptTime(date);
            registryInfoAtom.insertRegistryInfo(info);

            FsWorkFlowLog fsWorkFlowLog = new FsWorkFlowLog();
            fsWorkFlowLog.setWfGuid(UniqueId.getInstance().nextId().toString());
            fsWorkFlowLog.setWfLogId(1L);
            fsWorkFlowLog.setWfLogText("cs实物");
            fsWorkFlowLog.setWfTime(DateUtils.getTimestamp());
            fsWorkFlowLogMapper.insert(fsWorkFlowLog);

            fsTransactionManager.doSuccess();
        } catch (Exception e) {
            throw new FsServiceException(e.getMessage());
        }
        ListenableFuture<Object> obj = asyncTaskService.commit("privateAsyncService", () -> {
            RegistryInfo s = null;
            try {
                 s = registryInfoAtom.getRegistryInfoByKey("5567766811362984960");
            }catch (RejectedExecutionException e){
                throw new FsServiceException("创建异步线程失败");
            }
            return s;
        });
        log.info("obj:{}",obj);
        long endTime = MilliSecondUtils.currentTimeMillis();
        log.info("结束时间："+endTime+"一共需要："+ (endTime-startTime));
        return 1;
    }



    @Override
    public void mySqlDataBaseBackUp(DataBaseBackUpDTO inputParam) {
        //查询备份主机登录信息
        RegistryInfo registryInfo = registryInfoAtom.getRegistryInfoByKey("MYSQL_HOST_INFO");
        if (registryInfo == null || StringUtils.isBlank(registryInfo.getRegkeyValue())) {
            throw new FsServiceException("MYSQL_HOST_INFO 信息不能为null");
        }
        // 获取主机连接信息
        ConnectInfoDTO connectInfoDTO = ConnectInfoSetUtils.dealConnectInfo(registryInfo.getRegkeyValue(), "\\|");

        DatabaseBackupInfo databaseBackupInfo = databaseBackupInfoAtom.getDataBaseInfoByKey(inputParam.getDatabaseName(), inputParam.getDatabaseId(), inputParam.getDatabasePort());
        if(databaseBackupInfo == null){
            throw new FsServiceException("未获取到备份数据库信息");
        }
        // 获取数据库连接信息
        ConnectInfoDTO dataBaseConnectInfo = dealDatabaseConnectInfo(databaseBackupInfo);

        // 解析备份目录
        String backupDir = dealBackupDir(databaseBackupInfo.getBackupDir());

        // 解析备份文件名(无后缀)
        String fileName = databaseBackupInfo.getBackupName().replace("@DATE", String.valueOf(DateUtils.getSysDate()))
                .replace("@TIME", String.valueOf(DateUtils.getTime()));

        String dbBackUpFileName = fileName + "." + databaseBackupInfo.getBackupFileSuffix();
        com.x.fs.base.utils.StringUtils.stringFormCheck(dbBackUpFileName, "文件名称中");

        String log = queryBackupLogDir();
        File backupLogFileName;
        BufferedWriter backupLogBw;
        try {
            backupLogFileName = new File(log, fileName + ".log");
            backupLogBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(backupLogFileName), FsBaseConstant.CHARACTER_ENCODE));
        } catch (Exception e) {
            throw new FsServiceException("创建文件或读取文件失败[" + log + "]，原因是：" + e.getMessage());
        }

        //加载mysqldump命令参数
        String mysqldump = " --complete-insert --add-drop-table --skip-add-locks --set-gtid-purged=OFF ";

        checkTableLock(dataBaseConnectInfo, inputParam.getDatabaseName());

        long dataSize = queryBackupDataSize(dataBaseConnectInfo, inputParam.getDatabaseName());

        doBackup(connectInfoDTO,dataBaseConnectInfo, backupLogBw, databaseBackupInfo.getDatabaseName(), mysqldump, backupDir, dbBackUpFileName, dataSize);

        checkBackupOk(connectInfoDTO,dataBaseConnectInfo,databaseBackupInfo.getDatabaseName(), backupDir, dbBackUpFileName);

        closeLogFileBw(backupLogBw);


    }


    /**
     * 数据库备份
     *
     * @param connectInfoDTO 备份主机连接信息
     */
    private void doBackup(ConnectInfoDTO connectInfoDTO,ConnectInfoDTO dataBaseConnectInfo, BufferedWriter backupLogBw, String databaseName, String mysqldump, String backupDir, String dbBackUpFileName, long dataSize) {
        Session session = null;
        ChannelShell channelShell = null;
        PipedInputStream pipeIn = null;
        PipedOutputStream pipeOut = null;
        ByteArrayOutputStream result = null;
        String cmd = " ";
        CmdResult pvResult;
        int timeOut = 20 * 1000;
        try {
            session = SSHUtils.openSession(connectInfoDTO, timeOut);

            pipeIn = new PipedInputStream();
            pipeOut = new PipedOutputStream(pipeIn);
            result = new ByteArrayOutputStream();
            channelShell = SSHUtils.openChannelShell(session, pipeIn, result, timeOut);

            cmd = "mkdir -p " + backupDir + " \n";
            pipeOut.write(cmd.getBytes());
            delayForWrite(result, 10000, cmd);
            if (result.toString().contains("mkdir:")) {
                throw new FsServiceException("创建文件夹失败：" + result);
            }
            result.reset();
            delayForReset(result, 10000, cmd);

            cmd = "pv -h \n";
            pipeOut.write(cmd.getBytes());
            delayForWrite(result, 10000, cmd);
            checkInfo(result);
            result.reset();
            delayForReset(result, 10000, cmd);

            cmd = "mysqldump --help \n";
            pipeOut.write(cmd.getBytes());
            delayForWrite(result, 10000, cmd);
            checkInfo(result);
            result.reset();
            delayForReset(result, 10000, cmd);

            String host = Objects.equals(connectInfoDTO.getHost(), connectInfoDTO.getHost()) ? "127.0.0.1" : dataBaseConnectInfo.getHost();
            cmd = "mysqldump -h" + host + " -u" + dataBaseConnectInfo.getUserName() + " -p'" + dataBaseConnectInfo.getPassWord() + "' -P" + dataBaseConnectInfo.getPort() + " " + databaseName + " " + mysqldump + " | pv -t -n --bytes > " + backupDir + "/" + dbBackUpFileName + "\n";
            pipeOut.write(cmd.getBytes());
            Thread.sleep(5000);

            // 处理备份进度(此时表被锁住了，不能写进度)
            BigDecimal cons = new BigDecimal("0.0");
            do {
                // 隔1秒查询一次进度
                Thread.sleep(500);

                // 如果没有进度，最长等待10秒
                delayForPv(result, 10000, hidePassword(cmd, dataBaseConnectInfo.getPassWord()));

                // 可能有ERROR信息不在最后一行
                if (hasError(result.toString())) {
                    break;
                }

                // 解析进度的最后一行
                pvResult = parsePvResult(com.x.fs.base.utils.StringUtils.getLastLine(result.toString()));

                // 当进度为 PV格式 或 警告信息 则继续运行
                if (pvResult.isValid()) {
                    backupLogBw.write("已备份 " + databaseName + " " + calcPercentage(pvResult.getByteNum(), dataSize) + ", 耗时 " + pvResult.getConsTime() + " 秒\n");
                    backupLogBw.flush();
                    cons = pvResult.getConsTime();
                    result.reset();
                    Thread.sleep(500);
                }
            } while (pvResult.isValid());

            if (hasError(result.toString())) {
                throw new FsServiceException("数据库备份失败[" + result + "]");
            } else {
                cons = cons.multiply(new BigDecimal("1.1"));
                backupLogBw.write("已备份 " + databaseName + " " + "100.00%, 耗时 " + cons.max(new BigDecimal("0.01")) + " 秒\n");
                backupLogBw.flush();
            }
        } catch (InterruptedException e) {
            throw new FsServiceException("备份线程被中断 ==>  " + e.getMessage());
        } catch (Exception e) {
            throw new FsServiceException("命令[" + hidePassword(cmd, dataBaseConnectInfo.getPassWord()) + "]执行失败 ==>  " + e.getMessage());
        } finally {
            if (pipeIn != null) {
                try {
                    pipeIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pipeOut != null) {
                try {
                    pipeOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channelShell != null) {
                try {
                    channelShell.disconnect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (session != null) {
                try {
                    session.disconnect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private void checkInfo(ByteArrayOutputStream result){
        if (!result.toString().contains("Usage:") && !result.toString().contains("用法:")) {
            throw new FsServiceException("执行命令失败：" + result);
        }
    }

    /**
     * 检查数据库备份完整性
     */
    private void checkBackupOk(ConnectInfoDTO connectInfoDTO,ConnectInfoDTO dataBaseConnectInfo, String databaseName, String backupDir, String backupFileName) {
        List<String> allTables = queryBackupTables(dataBaseConnectInfo, databaseName);

        String cmd = " grep -i \"CREATE TABLE \" " + backupDir + "/" + backupFileName + " \n";
        SSHExecuteResult sshExecuteResult = SSHUtils.execCommand(connectInfoDTO, cmd, 10 * 60 * 1000);
        if (StringUtils.isNotBlank(sshExecuteResult.getErrLog())) {
            throw new FsServiceException("检索备份文件失败[" + cmd + "]");
        }
        String backupTables = " " + sshExecuteResult.getRunLog().trim()
                .replace("CREATE TABLE ", "")
                .replace("`", "")
                .replace("(", "")
                .replaceAll("\\s+", " ")
                .trim().toLowerCase() + " ";

        StringJoiner unBackupTables = new StringJoiner(",");
        int unBackupTableCount = 0;
        for (String table : allTables) {
            if (!backupTables.contains(" " + table.trim().toLowerCase() + " ")) {
                unBackupTables.add(table.trim().toLowerCase());
                unBackupTableCount++;
            }
        }
        if (StringUtils.isNotBlank(unBackupTables.toString())) {
            throw new FsServiceException("核对备份文件失败，存在 " + unBackupTableCount + " 张表未成功备份[" + unBackupTables.toString() + "]");
        }

        cmd = "tail -2 " + backupDir + "/" + backupFileName + " \n";
        SSHExecuteResult sshExecute = SSHUtils.execCommand(connectInfoDTO, cmd, 60 * 1000);
        if (StringUtils.isNotBlank(sshExecute.getErrLog())) {
            throw new FsServiceException("查询备份文件结尾标记失败[" + cmd + "]");
        }
        String endFlagStr = com.x.fs.base.utils.StringUtils.getLastLine(sshExecute.getRunLog());
        if (!endFlagStr.contains("Dump completed") && !endFlagStr.contains("完成")) {
            throw new FsServiceException("核对备份文件结尾标记失败[" + endFlagStr + "]");
        }
    }


    public static void delayForReset(ByteArrayOutputStream result, int time, String cmd) throws InterruptedException {
        int count = 0;
        while (true) {
            Thread.sleep(100);
            count += 100;
            if (StringUtils.isBlank(result.toString())) {
                return;
            }
            if (count > time) {
                throw new FsServiceException("清空[" + cmd + "]命令缓存区超时");
            }
        }
    }

    public static void delayForPv(ByteArrayOutputStream result, int time, String cmd) throws InterruptedException {
        int count = 0;
        while (true) {
            Thread.sleep(100);
            count += 100;
            if (StringUtils.isNotBlank(result.toString())) {
                return;
            }
            if (count > time) {
                throw new FsServiceException("执行[" + cmd + "]命令时查询进度超时");
            }
        }
    }


    public static void delayForWrite(ByteArrayOutputStream result, int time, String cmd) throws InterruptedException {
        int count = 0;
        Thread.sleep(500);

        while (true) {
            Thread.sleep(100);
            count += 100;
            if (result.toString().trim().endsWith("$") || result.toString().trim().endsWith("#")) {
                return;
            }

            if (count > time) {
                throw new FsServiceException("执行[" + cmd + "]命令超时");
            }
        }
    }


    private ConnectInfoDTO dealDatabaseConnectInfo(DatabaseBackupInfo databaseBackupInfo) {
        ConnectInfoDTO connectInfoDTO = ConnectInfoSetUtils.dealBackeupConnectInfo(databaseBackupInfo.getDatabaseId(), "\\|");
        connectInfoDTO.setPort(Integer.parseInt(databaseBackupInfo.getDatabasePort()));
        return connectInfoDTO;
    }


    private String dealBackupDir(String backUpDir) {
        String backUpDirFile = backUpDir.replace("\\", "/").replace("//", "/").trim();
        if (backUpDirFile.endsWith("/")) {
            backUpDirFile = backUpDirFile.substring(0, backUpDirFile.length() - 1);
        }
        com.x.fs.base.utils.StringUtils.stringFormCheck(backUpDir, "文件名称中");
        return backUpDirFile;
    }

    public String queryBackupLogDir() {
        // 读取注册项中的备份日志路径
        RegistryInfo backupLogDir = registryInfoAtom.getRegistryInfoByKey("DATABASE_BACKUP_LOG_DIRE");
        if (backupLogDir == null || StringUtils.isBlank(backupLogDir.getRegkeyValue())) {
            throw new FsServiceException("未配置数据库备份日志目录[BACKUP_LOG_DIRE:" + backupLogDir + "]");
        }
        // 如果日志目录不存在，则创建
        try {
            File backupLogDirFile = new File(backupLogDir.getRegkeyValue());
            if (!backupLogDirFile.exists() && !backupLogDirFile.isDirectory()) {
                backupLogDirFile.mkdirs();
            }
        } catch (Exception e) {
            log.debug("创建数据库备份日志目录是[" + backupLogDir.getRegkeyValue() + "]，失败原因[" + e.getMessage() + "]");
        }
        return backupLogDir.getRegkeyValue();
    }

    /**
     * 获取备份数据库所有表
     * @param dataBaseConnectInfo
     * @param databaseName
     * @return
     */
    private List<String> queryBackupTables(ConnectInfoDTO dataBaseConnectInfo, String databaseName) {
        try {
            return (List<String>) MySqlJdbcUtils.executeQuery(dataBaseConnectInfo, databaseName,
                    " SELECT DISTINCT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = '" + databaseName + "' ",
                    this::queryBackupTablesHandleResult);
        } catch (Exception exception) {
            throw new FsServiceException("获取备份数据库 " + databaseName + " 表列表失败[" + exception.getMessage() + "]");
        }
    }

    /**
     * 获取已经锁住的表
     * @param dataBaseConnectInfo
     * @param databaseName
     */
    private void checkTableLock(ConnectInfoDTO dataBaseConnectInfo, String databaseName) {
        // 读取表级锁
        List<String> lockedTables = null;
        try {
            lockedTables = (List<String>) MySqlJdbcUtils.executeQuery(dataBaseConnectInfo, databaseName,
                    " show OPEN TABLES where In_use > 0 ",
                    MysqlDataBaseServiceImpl::queryTableLockHandleResult);
        } catch (Exception exception) {
            throw new FsServiceException("无法获取[" + databaseName + "]数据库表锁定信息[" + exception.getMessage() + "]");
        }
        if (!lockedTables.isEmpty()) {
            StringJoiner string = new StringJoiner(",");
            lockedTables.forEach(string::add);
            throw new FsServiceException("数据库表已被锁定[" + string + "]");
        }
    }

    /**
     * 获取预估备份数据大小
     * @param dataBaseConnectInfo
     * @param databaseName
     * @return
     */
    private long queryBackupDataSize(ConnectInfoDTO dataBaseConnectInfo, String databaseName) {
        Long size;
        try {
            size = (Long) MySqlJdbcUtils.executeQuery(dataBaseConnectInfo, databaseName,
                    " SELECT SUM(data_length + index_length) AS size FROM information_schema.TABLES WHERE table_schema = '" + databaseName + "' ",
                    this::queryBackupDataSizeHandleResult);
        } catch (Exception exception) {
            throw new FsServiceException("无法获取" + databaseName + "数据库备份文件预估大小[" + exception.getMessage() + "]");
        }
        if (size == null) {
            throw new FsServiceException("无法获取 " + databaseName + " 数据库备份文件预估大小");
        }
        return size;
    }

    /**
     * 处理查询结果
     * @param resultSet
     * @return
     */
    private static Object queryTableLockHandleResult(ResultSet resultSet) {
        ArrayList<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString("Table"));
            }
        } catch (Exception e) {
            throw new FsServiceException("处理查询结构信息出错：[" + e.getMessage() + "]");
        }
        return list;
    }

    /**
     * 处理查询结果
     * @param resultSet
     * @return
     */
    private Object queryBackupDataSizeHandleResult(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return resultSet.getLong("size");
            }
        } catch (Exception e) {
            throw new FsServiceException("处理查询数据库大小结果失败" + e.getMessage());
        }
        return null;
    }

    /**
     * 处理查询结果
     * @param resultSet
     * @return
     */
    private List<String> queryBackupTablesHandleResult(ResultSet resultSet) {
        ArrayList<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (Exception exception) {
            throw new FsServiceException(exception.getMessage());
        }
        return list;
    }


    /**
     * 关闭日志文件
     */
    public void closeLogFileBw(BufferedWriter backupLogBw) {
        if (backupLogBw != null) {
            try {
                backupLogBw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 隐藏备份命令中的密码
     * @param cmd
     * @return
     */
    private String hidePassword(String cmd, String password) {
        if (cmd == null) {
            return cmd;
        }
        return cmd.replace(" -p'" + password + "' ", " -p****** ");
    }

    /**
     * 判断返回结果是否包含ERROR字样
     *
     * @param result
     * @return
     */
    public boolean hasError(String result) {
        return result.contains("error") || result.contains("ERROR") || result.contains("错误");
    }

    /**
     * 转换LONG为百分比
     *
     * @param byteNum
     * @param size
     * @return
     */
    public static String calcPercentage(Long byteNum, Long size) {
        if (byteNum == null || size == null) {
            return "0.00%";
        }
        if (size == 0) {
            return "99.99%";
        }
        double result = byteNum / (size * 1.0);
        result = Math.min(0.9999, result) * 100;
        return String.format("%.2f%%", result);
    }

    /**
     * 判断响应是否为想要的PV格式 2.0107 59622378
     *
     * @param res
     * @return
     */
    public static CmdResult parsePvResult(String res) {
        CmdResult pvResult = new CmdResult();
        try {
            if (res.contains("[Warning]") || res.contains("[警告]")) {
                pvResult.setValid(true);
                pvResult.setByteNum(0L);
                pvResult.setConsTime(new BigDecimal("0.0"));
                return pvResult;
            }
            String[] split = res.trim().split(" ");
            if (split.length != 2) {
                pvResult.setValid(false);
                return pvResult;
            }
            pvResult.setConsTime(new BigDecimal(split[0]));
            pvResult.setByteNum(Long.parseLong(split[1]));
            pvResult.setValid(true);
            return pvResult;
        } catch (Exception exception) {
            pvResult.setValid(false);
            return pvResult;
        }
    }


}
