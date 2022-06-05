package com.x.fs.base.utils;

import com.x.fs.base.constant.FsBaseConstant;
import com.x.fs.common.exception.FsServiceException;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * 数据库类型
 * @author x
 */
public class DataBaseConnectUtils {

    private static String dataBaseProductName;


    /**
     * 判断当前环境是否为MySql
     *
     * @return
     */
    public static boolean isMySql() {
        String databaseProductName = getDatabaseProductName();
        if (StringUtils.isBlank(databaseProductName)) {
            throw new FsServiceException("获取的数据库类型错误");
        }

        return databaseProductName.contains(FsBaseConstant.MYSQL);
    }

    /**
     * 判断当前环境是否为SqlServer
     *
     * @return
     */
    public static boolean isSqlServer() {
        String databaseProductName = getDatabaseProductName();
        if (StringUtils.isBlank(databaseProductName)) {
            throw new FsServiceException("获取数据库类型错误");
        }

        return databaseProductName.contains(FsBaseConstant.SQL_SERVER);
    }

    /**
     * 判断当前环境是否为Oracle
     *
     * @return
     */
    public static boolean isOracle() {
        String databaseProductName = getDatabaseProductName();
        if (StringUtils.isBlank(databaseProductName)) {
            throw new FsServiceException("获取数据库类型错误");
        }

        return databaseProductName.contains(FsBaseConstant.ORACLE);
    }

    /**
     * 自动切换适配SQL
     *
     * @param mysql
     * @param sqlServer
     * @param oracle
     * @return
     */
    public static String sqlAdapter(String mysql, String sqlServer, String oracle) {
        String databaseProductName = getDatabaseProductName();
        if (StringUtils.isBlank(databaseProductName)) {
            throw new FsServiceException("获取数据库类型错误");
        }

        if (databaseProductName.contains(FsBaseConstant.MYSQL)) {
            return mysql;
        } else if (databaseProductName.contains(FsBaseConstant.SQL_SERVER)) {
            return sqlServer;
        } else if (databaseProductName.contains(FsBaseConstant.ORACLE)) {
            return oracle;
        } else {
            throw new FsServiceException("暂不支持该数据库[" + databaseProductName + "]");
        }
    }

    /**
     * 自动切换适配SQL
     *
     * @param mysql
     * @param sqlServer
     * @param oracle
     * @return
     */
    public static String sqlAdapter(Supplier<String> mysql, Supplier<String> sqlServer, Supplier<String> oracle) {
        String databaseProductName = getDatabaseProductName();
        if (StringUtils.isBlank(databaseProductName)) {
            throw new FsServiceException("获取数据库类型错误");
        }

        if (databaseProductName.contains(FsBaseConstant.MYSQL)) {
            return mysql.get();
        } else if (databaseProductName.contains(FsBaseConstant.SQL_SERVER)) {
            return sqlServer.get();
        } else if (databaseProductName.contains(FsBaseConstant.ORACLE)) {
            return oracle.get();
        } else {
            throw new FsServiceException("暂不支持该数据库[" + databaseProductName + "]");
        }
    }

    /**
     * 从连接获取数据库类型
     *
     * @return
     */
    public synchronized static String getDatabaseProductName() {
        if (StringUtils.isBlank(dataBaseProductName)) {

            SqlSessionTemplate defaultSqlSessionTemplate = FsApplicationContext.getBean(SqlSessionTemplate.class);
            SqlSession sqlSession = null;
            try {
                sqlSession = SqlSessionUtils.getSqlSession(
                        defaultSqlSessionTemplate.getSqlSessionFactory(),
                        defaultSqlSessionTemplate.getExecutorType(),
                        defaultSqlSessionTemplate.getPersistenceExceptionTranslator());
                Connection conn = sqlSession.getConnection();
                dataBaseProductName = conn.getMetaData().getDatabaseProductName();
            } catch (SQLException e) {
                throw new FsServiceException(e.getMessage());
            } finally {
                if (sqlSession != null) {
                    SqlSessionUtils.closeSqlSession(sqlSession, defaultSqlSessionTemplate.getSqlSessionFactory());
                }
            }
        }
        return dataBaseProductName;
    }


}
