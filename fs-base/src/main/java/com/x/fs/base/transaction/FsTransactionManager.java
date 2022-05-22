package com.x.fs.base.transaction;

import com.x.fs.base.datasource.DataTransManager;
import com.x.fs.base.datasource.utils.DataSourceUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.io.Closeable;

/**
 * @author x
 */
public class FsTransactionManager implements AutoCloseable{

    private String dataSourceId;

    private boolean rollBackFlag = true;

    private TransactionStatus transactionStatus;

    public FsTransactionManager() {
        DataTransManager.startTrans();
    }

    public FsTransactionManager(String dataSourceId) {
        if (StringUtils.isBlank(dataSourceId)) {
            DataSourceUtils.set(dataSourceId);
        }
        this.dataSourceId = dataSourceId;
        DataTransManager.startTrans();
    }

    public FsTransactionManager(String dataSourceId, Propagation propagation, Isolation isolation) {
        if (StringUtils.isBlank(dataSourceId)) {
            DataSourceUtils.set(dataSourceId);
        }
        this.dataSourceId = dataSourceId;
        transactionStatus = DataTransManager.startTrans(DataSourceUtils.get(dataSourceId), propagation, isolation);
    }

    public void doSuccess() {
        if (StringUtils.isNotBlank(dataSourceId)) {
            DataTransManager.commitTrans(DataSourceUtils.get(dataSourceId), transactionStatus);
        } else {
            DataTransManager.commitTrans();
        }
        rollBackFlag = false;
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * <p>While this interface method is declared to throw {@code
     * Exception}, implementers are <em>strongly</em> encouraged to
     * declare concrete implementations of the {@code close} method to
     * throw more specific exceptions, or to throw no exception at all
     * if the close operation cannot fail.
     *
     * <p> Cases where the close operation may fail require careful
     * attention by implementers. It is strongly advised to relinquish
     * the underlying resources and to internally <em>mark</em> the
     * resource as closed, prior to throwing the exception. The {@code
     * close} method is unlikely to be invoked more than once and so
     * this ensures that the resources are released in a timely manner.
     * Furthermore it reduces problems that could arise when the resource
     * wraps, or is wrapped, by another resource.
     *
     * <p><em>Implementers of this interface are also strongly advised
     * to not have the {@code close} method throw {@link
     * InterruptedException}.</em>
     * <p>
     * This exception interacts with a thread's interrupted status,
     * and runtime misbehavior is likely to occur if an {@code
     * InterruptedException} is {@linkplain Throwable#addSuppressed
     * suppressed}.
     * <p>
     * More generally, if it would cause problems for an
     * exception to be suppressed, the {@code AutoCloseable.close}
     * method should not throw it.
     *
     * <p>Note that unlike the {@link Closeable#close close}
     * method of {@link Closeable}, this {@code close} method
     * is <em>not</em> required to be idempotent.  In other words,
     * calling this {@code close} method more than once may have some
     * visible side effect, unlike {@code Closeable.close} which is
     * required to have no effect if called more than once.
     * <p>
     * However, implementers of this interface are strongly encouraged
     * to make their {@code close} methods idempotent.
     *
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
       if(rollBackFlag){
           DataTransManager.rollbackTrans();
       }
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }


}
