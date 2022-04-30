package com.x.fs.base.config;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.x.fs.base.datasource.DataSourceContextHolder;
import com.x.fs.base.datasource.DataSourceUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.util.Assert;

/**
 * 创建自定义的 SqlSessionTemplate
 * @author x
 */
public class CustomSqlSessionTemplate extends SqlSessionTemplate {
    private final SqlSessionFactory sqlSessionFactory;
    private final ExecutorType executorType;
    private final SqlSession sqlSessionProxy;
    private final PersistenceExceptionTranslator defaultExceptionTranslator;
    private Map<String, PersistenceExceptionTranslator> exceptionTranslatorMap;
    private Map<Object, SqlSessionFactory> targetSqlSessionFactorys;
    private SqlSessionFactory defaultTargetSqlSessionFactory;
    private MoreDataSourceProperties moreDataSourceProperties;
    private String defaultDataSourceId;

    public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator) {
        super(sqlSessionFactory, executorType, exceptionTranslator);
        this.sqlSessionFactory = sqlSessionFactory;
        this.executorType = executorType;
        this.defaultExceptionTranslator = exceptionTranslator;
        this.sqlSessionProxy = (SqlSession) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(), new Class[]{SqlSession.class}, new CustomSqlSessionTemplate.SqlSessionInterceptor());
        this.defaultTargetSqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        String dataSourceName = DataSourceContextHolder.getDataSource();
        SqlSessionFactory targetSqlSessionFactory = this.targetSqlSessionFactorys.get(DataSourceUtils.getSqlSessionFactoryName(dataSourceName));
        SqlSessionFactory defaultDataSource =  this.targetSqlSessionFactorys.get(DataSourceUtils.getSqlSessionFactoryName(this.moreDataSourceProperties.getDefaultDataSourceId()));
        if (targetSqlSessionFactory != null) {
            return targetSqlSessionFactory;
        } else if (defaultDataSource != null) {
            return defaultDataSource;
        } else if (this.defaultTargetSqlSessionFactory != null) {
            return this.defaultTargetSqlSessionFactory;
        } else {
            Assert.notNull(this.targetSqlSessionFactorys, "Property 'targetSqlSessionFactorys' or 'defaultTargetSqlSessionFactory' are required");
            Assert.notNull(this.defaultTargetSqlSessionFactory, "Property 'defaultTargetSqlSessionFactory' or 'targetSqlSessionFactorys' are required");
            return this.sqlSessionFactory;
        }
    }

    @Override
    public Configuration getConfiguration() {
        return this.getSqlSessionFactory().getConfiguration();
    }

    @Override
    public ExecutorType getExecutorType() {
        return this.executorType;
    }

    @Override
    public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
        String dataSourceName = DataSourceContextHolder.getDataSource();
        PersistenceExceptionTranslator exceptionTranslator = (PersistenceExceptionTranslator) this.exceptionTranslatorMap.get(DataSourceUtils.getSqlSessionFactoryName(dataSourceName));
        return exceptionTranslator != null ? exceptionTranslator : this.defaultExceptionTranslator;
    }

    @Override
    public <T> T selectOne(String statement) {
        return this.sqlSessionProxy.selectOne(statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return this.sqlSessionProxy.selectOne(statement, parameter);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return this.sqlSessionProxy.selectMap(statement, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return this.sqlSessionProxy.selectMap(statement, parameter, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return this.sqlSessionProxy.selectMap(statement, parameter, mapKey, rowBounds);
    }

    @Override
    public <E> List<E> selectList(String statement) {
        return this.sqlSessionProxy.selectList(statement);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return this.sqlSessionProxy.selectList(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return this.sqlSessionProxy.selectList(statement, parameter, rowBounds);
    }

    @Override
    public void select(String statement, ResultHandler handler) {
        this.sqlSessionProxy.select(statement, handler);
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        this.sqlSessionProxy.select(statement, parameter, handler);
    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        this.sqlSessionProxy.select(statement, parameter, rowBounds, handler);
    }

    @Override
    public int insert(String statement) {
        return this.sqlSessionProxy.insert(statement);
    }

    @Override
    public int insert(String statement, Object parameter) {
        return this.sqlSessionProxy.insert(statement, parameter);
    }

    @Override
    public int update(String statement) {
        return this.sqlSessionProxy.update(statement);
    }

    @Override
    public int update(String statement, Object parameter) {
        return this.sqlSessionProxy.update(statement, parameter);
    }

    @Override
    public int delete(String statement) {
        return this.sqlSessionProxy.delete(statement);
    }

    @Override
    public int delete(String statement, Object parameter) {
        return this.sqlSessionProxy.delete(statement, parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return this.getConfiguration().getMapper(type, this);
    }

    @Override
    public void commit() {
        throw new UnsupportedOperationException("Fs commit is not allowed over a Spring managed SqlSession");
    }

    @Override
    public void commit(boolean force) {
        throw new UnsupportedOperationException("Fs commit is not allowed over a Spring managed SqlSession");
    }

    @Override
    public void rollback() {
        throw new UnsupportedOperationException("Fs rollback is not allowed over a Spring managed SqlSession");
    }

    @Override
    public void rollback(boolean force) {
        throw new UnsupportedOperationException("Fs rollback is not allowed over a Spring managed SqlSession");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Fs close is not allowed over a Spring managed SqlSession");
    }

    @Override
    public void clearCache() {
        this.sqlSessionProxy.clearCache();
    }

    @Override
    public Connection getConnection() {
        return this.sqlSessionProxy.getConnection();
    }

    @Override
    public List<BatchResult> flushStatements() {
        return this.sqlSessionProxy.flushStatements();
    }

    public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        this(sqlSessionFactory, executorType, new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true));
    }

    public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
    }

    public void setTargetSqlSessionFactorys(Map<Object, SqlSessionFactory> targetSqlSessionFactorys) {
        this.targetSqlSessionFactorys = targetSqlSessionFactorys;
    }

    public void setExceptionTranslatorMap(Map<String, PersistenceExceptionTranslator> exceptionTranslatorMap) {
        this.exceptionTranslatorMap = exceptionTranslatorMap;
    }

    public void setDefaultDataSourceId(String defaultDataSourceId) {
        this.defaultDataSourceId = defaultDataSourceId;
    }

    public MoreDataSourceProperties getMultiDataSourceProperties() {
        return this.moreDataSourceProperties;
    }

    public void setMultiDataSourceProperties(MoreDataSourceProperties moreDataSourceProperties) {
        this.moreDataSourceProperties = moreDataSourceProperties;
    }

    private class SqlSessionInterceptor implements InvocationHandler {
        private SqlSessionInterceptor() {
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            SqlSessionFactory properSqlSessionFactory = CustomSqlSessionTemplate.this.getSqlSessionFactory();
            ExecutorType executorType = CustomSqlSessionTemplate.this.getExecutorType();
            PersistenceExceptionTranslator exceptionTranslator = CustomSqlSessionTemplate.this.getPersistenceExceptionTranslator();
            SqlSession sqlSession = SqlSessionUtils.getSqlSession(properSqlSessionFactory, executorType, exceptionTranslator);
            Object unwrapped;
            try {
                Object result = method.invoke(sqlSession, args);
                if (!SqlSessionUtils.isSqlSessionTransactional(sqlSession, properSqlSessionFactory)) {
                    sqlSession.commit(true);
                }
                unwrapped = result;
            } catch (Throwable var14) {
                unwrapped = ExceptionUtil.unwrapThrowable(var14);
                if (exceptionTranslator != null && unwrapped instanceof PersistenceException) {
                    Throwable translated = exceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
                    if (translated != null) {
                        unwrapped = translated;
                    }
                }
                throw (Throwable) unwrapped;
            } finally {
                SqlSessionUtils.closeSqlSession(sqlSession, properSqlSessionFactory);
            }
            return unwrapped;
        }
    }
}

