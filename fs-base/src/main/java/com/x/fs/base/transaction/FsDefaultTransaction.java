package com.x.fs.base.transaction;

import com.x.fs.common.exception.FsServiceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.function.Consumer;

import static com.x.fs.base.datasource.DataTransManager.getTransactionManager;

/**
 * @author x
 */
public interface FsDefaultTransaction {
    Logger log = LoggerFactory.getLogger(FsDefaultTransaction.class);


    /**
     * 开启事务
     *
     * @return
     */
    default TransactionStatus beginTransaction() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new FsServiceException("当前线程事务处于开启状态");
        }

        log.info("事务日志-开启事务 ThreadId[" + Thread.currentThread().getId() + "] 当前事务状态[" + TransactionSynchronizationManager.isActualTransactionActive() + "]");
        TransactionStatus transactionStatus = getTransactionManager().getTransaction(new DefaultTransactionDefinition());
        log.info("事务已开启[" + transactionStatus + "]");

        return transactionStatus;
    }

    /**
     * 提交事务
     *
     * @param transactionStatus
     */
    default void commitTransaction(TransactionStatus transactionStatus) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new FsServiceException("当前线程事务处于关闭状态");
        }

        log.info("事务日志-提交事务 ThreadId[" + Thread.currentThread().getId() + "] 当前事务状态[" + TransactionSynchronizationManager.isActualTransactionActive() + "]");
        getTransactionManager().commit(transactionStatus);
    }

    /**
     * 回滚事务
     *
     * @param transactionStatus
     */
    default void rollbackTransaction(TransactionStatus transactionStatus) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new FsServiceException("当前线程事务处于关闭状态");
        }

        log.info("事务日志-回滚事务 ThreadId[" + Thread.currentThread().getId() + "] 当前事务状态[" + TransactionSynchronizationManager.isActualTransactionActive() + "]");
        log.info("当前事务[" + transactionStatus + "]");
        getTransactionManager().rollback(transactionStatus);
    }

    /**
     * 处理事务回滚操作
     *
     * @param transactionStatus
     * @param exception
     */
    default void handleTransactionRollback(TransactionStatus transactionStatus, Exception exception) {
        rollbackTransaction(transactionStatus);

        if (!(exception instanceof FsServiceException)) {
            throw new FsServiceException(exception.getMessage());
        }

        throw (FsServiceException) exception;
    }

    /**
     * 开启事务执行
     *
     * @param runnable
     */
    default void runWithTransaction(Runnable runnable) {
        runWithTransaction(runnable, () -> {
        });
    }

    /**
     * 开启事务执行
     *
     * @param runnable
     * @param exceptionHandler
     */
    default void runWithTransaction(Runnable runnable, Runnable exceptionHandler) {
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager()) {
            runnable.run();
            fsTransactionManager.doSuccess();
        } catch (Exception e) {
            exceptionHandler.run();
            throw new FsServiceException(e.getMessage());
        }
    }

    /**
     * 开启事务执行
     *
     * @param runnable
     * @param exceptionHandler
     */
    default void runWithTransaction(Runnable runnable, Consumer<Exception> exceptionHandler) {
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager()) {
            runnable.run();
            fsTransactionManager.doSuccess();
        } catch (Exception e) {
            exceptionHandler.accept(e);
            throw new FsServiceException(e.getMessage());
        }
    }

    /**
     * 开启事务执行
     *
     * @param runnable
     * @param exceptionHandler
     */
    default void runWithTransaction(Runnable runnable, Consumer<Exception> exceptionHandler, Runnable f) {
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager()) {
            runnable.run();
            fsTransactionManager.doSuccess();
        } catch (Exception e) {
            exceptionHandler.accept(e);
            throw new FsServiceException(e.getMessage());
        } finally {
            f.run();
        }
    }

    /**
     * 无事务执行
     *
     * @param runnable
     */
    default void runNoTransaction(String dataSourceId, Runnable runnable) {
        runNoTransaction(dataSourceId, runnable, () -> {
        });
    }

    /**
     * 无事务执行
     *
     * @param runnable
     * @param exceptionHandler
     */
    default void runNoTransaction(String dataSourceId, Runnable runnable, Runnable exceptionHandler) {
        try (FsTransactionManager fsTransactionManager = new FsTransactionManager(dataSourceId, Propagation.NOT_SUPPORTED, Isolation.DEFAULT)) {
            runnable.run();
            fsTransactionManager.doSuccess();
        } catch (Exception e) {
            exceptionHandler.run();
            throw new FsServiceException(e.getMessage());
        }
    }

    /**
     * 新开事务执行
     *
     * @param runnable
     */
    default void runNewTransaction(String dataSourceId, Runnable runnable) {
        runNewTransaction(dataSourceId, runnable, () -> {
        });
    }

    /**
     * 新开事务执行
     *
     * @param runnable
     * @param exceptionHandler
     */
    default void runNewTransaction(String dataSourceId, Runnable runnable, Runnable exceptionHandler) {
        try (FsTransactionManager transactionManager = new FsTransactionManager(dataSourceId, Propagation.REQUIRES_NEW, Isolation.DEFAULT)) {
            runnable.run();
            transactionManager.doSuccess();
        } catch (Exception e) {
            exceptionHandler.run();
            throw new FsServiceException(e.getMessage());
        }
    }

    /**
     * 判断当前线程是否存在活动事务
     *
     * @return
     */
    default boolean isActualTransactionActive() {
        return TransactionSynchronizationManager.isActualTransactionActive();
    }

}
