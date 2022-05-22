package com.x.fs.base.datasource;

import com.x.fs.base.datasource.utils.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author x
 */
@Slf4j
public class DataTransManager {

    private static ThreadLocal<Map<DataSource, TransactionStatus>> fsLocalTransStatus =
            new ThreadLocal<>();

    private DataTransManager() {
    }


    protected static <T> Map<DataSource, T> getTransStatus(ThreadLocal<Map<DataSource, T>> threadLocal) {
        Map<DataSource, T> status = threadLocal.get();
        if (status == null) {
            // 保证顺序
            status = new LinkedHashMap<>();
            threadLocal.set(status);
        }
        return status;
    }

    /**
     * 获取事务管理器
     * @return 事务管理器
     */
    public static DataSourceTransactionManager getTransactionManager() {
        return getTransactionManager(null);
    }

    /**
     * 获取事务管理器
     * @param dataSource 数据源
     * @return 事务管理器
     */
    public static DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
        if (dataSource == null) {
            dataSource = DataSourceUtils.get();
        }
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 开始事务，使用当前数据源
     * 使用spring默认事务传播级别，隔离级别
     */
    public static void startTrans() {
        startTrans(DataSourceUtils.get());
    }

    /**
     * 开始事务, 使用指定数据源
     * 使用spring默认事务传播级别，隔离级别
     * @param dataSource 数据源
     */
    public static void startTrans(DataSource dataSource) {
        Map<DataSource, TransactionStatus> trans = getTransStatus(fsLocalTransStatus);
        if (!trans.containsKey(dataSource)) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            TransactionStatus status = getTransactionManager(dataSource).getTransaction(def);
            trans.put(dataSource, status);
        }
    }

    /**
     * 开始事务，使用指定数据源，使用指定隔离级别
     * 和{@link DataTransManager#commitTrans(DataSource, TransactionStatus)}
     * {@link DataTransManager#rollbackTrans(DataSource, TransactionStatus)}
     * @param dataSource 数据源
     * @param isolation 隔离级别
     */
    public static TransactionStatus startTrans(DataSource dataSource, Isolation isolation) {
        return startTrans(dataSource, Propagation.REQUIRED, isolation);
    }

    /**
     * 开始事务，使用指定数据源，使用指定传播级别，隔离级别
     * 和{@link DataTransManager#commitTrans(DataSource, TransactionStatus)}
     * {@link DataTransManager#rollbackTrans(DataSource, TransactionStatus)}
     * 配合使用
     * @param dataSource 数据源
     * @param propagation 传播级别
     * @param isolation 隔离级别
     */
    public static TransactionStatus startTrans(DataSource dataSource, Propagation propagation, Isolation isolation) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(propagation.value());
        def.setIsolationLevel(isolation.value());
        TransactionStatus status = getTransactionManager(dataSource).getTransaction(def);
        return status;
    }

    /**
     * 开始事务。
     */
    public static void startTrans(DataSource[] dataSource) {
        for (int i = 0; i < dataSource.length; i++) {
            startTrans(dataSource[i]);
        }
    }

    /**
     * 提交事务，指定数据源及其事务状态
     * @param dataSource 数据源
     * @param status 事务状态
     */
    public static void commitTrans(DataSource dataSource, TransactionStatus status) {
        getTransactionManager(dataSource).commit(status);
    }

    /**
     * 提交事务
     */
    public static void commitTrans() {
        Map<DataSource, TransactionStatus> trans = getTransStatus(fsLocalTransStatus);

        try {
            Object[] keys = trans.keySet().toArray();
            DataSource lastKey = (DataSource) keys[keys.length - 1];
            TransactionStatus tran = trans.get(lastKey);
            if (tran != null) {
                getTransactionManager(lastKey).commit(tran);
            }
            trans.remove(lastKey);
        } finally {
            if (trans.isEmpty()) {
                fsLocalTransStatus.remove();
                log.debug("事务已经全部提交完成");
            }
        }
    }

    /**
     * 提交全部事务
     */
    public static void commitAllTrans() {
        try {
            Map<DataSource, TransactionStatus> trans = getTransStatus(fsLocalTransStatus);
            Object[] keys = trans.keySet().toArray();
            for (int i = keys.length - 1; i > -1; i--) {
                DataSource key = (DataSource) keys[i];
                TransactionStatus tran = trans.get(key);
                if (tran != null) {
                    getTransactionManager(key).commit(tran);
                }
                trans.remove(key);
            }
        } finally {
            fsLocalTransStatus.remove();
        }
    }

    /**
     * 事务回滚
     */
    public static void rollbackTrans() {
        Map<DataSource, TransactionStatus> trans = getTransStatus(fsLocalTransStatus);
        try {
            Object[] keys = trans.keySet().toArray();
            DataSource lastKey = (DataSource) keys[keys.length - 1];
            TransactionStatus tran = trans.get(lastKey);
            if (tran != null) {
                getTransactionManager(lastKey).rollback(tran);
            }
            trans.remove(lastKey);
        } finally {
            if (trans.isEmpty()) {
                fsLocalTransStatus.remove();
                log.debug("事务已经全部回滚完成");
            }
        }
    }

    /**
     * 回滚事务, 指定数据源及其事务状态
     * @param dataSource 数据源
     * @param status 事务状态
     */
    public static void rollbackTrans(DataSource dataSource, TransactionStatus status) {
        getTransactionManager(dataSource).commit(status);
    }

    /**
     * 回滚全部的事务
     */
    public static void rollbackAllTrans() {
        try {
            Map<DataSource, TransactionStatus> trans = getTransStatus(fsLocalTransStatus);
            Object[] keys = trans.keySet().toArray();
            for (int i = keys.length - 1; i > -1; i--) {
                DataSource key = (DataSource) keys[i];
                TransactionStatus tran = trans.get(key);
                if (tran != null) {
                    getTransactionManager(key).rollback(tran);
                }
                trans.remove(key);
            }
        } finally {
            fsLocalTransStatus.remove();
        }
    }
}
