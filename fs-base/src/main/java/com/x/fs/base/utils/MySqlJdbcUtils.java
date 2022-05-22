package com.x.fs.base.utils;

import com.x.fs.base.dto.ConnectInfoDTO;
import com.x.fs.common.exception.FsServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * MySql连接工具
 *
 * @author x
 */
public class MySqlJdbcUtils {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行查询
     *
     * @param connectInfo
     * @param dbName
     * @param sql
     * @param resultHandler
     * @return
     */
    public static Object executeQuery(ConnectInfoDTO connectInfo, String dbName, String sql, Function<ResultSet, Object> resultHandler) {
        String url = "jdbc:mysql://" + connectInfo.getHost() + ":" + connectInfo.getPort() + "/" + dbName + "?userunicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, connectInfo.getUserName(), connectInfo.getPassWord());
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return resultHandler.apply(resultSet);
        } catch (Exception e) {
            throw new FsServiceException("MySqlJdbcUtils连接数据库失败[" + e.getMessage() + "]");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConnectInfoDTO connectInfo = new ConnectInfoDTO();
        connectInfo.setHost("ip");
        connectInfo.setPort(3306);
        connectInfo.setUserName("reader");
        connectInfo.setPassWord("reader");

        List<String> query = (List<String>) executeQuery(connectInfo, "fsdata", "SELECT DICT_NO  FROM FS_SYS_DICT",
                r -> {
                    ArrayList<String> result = new ArrayList<>();
                    try {
                        while (r.next()) {
                            result.add(r.getString("DICT_NO"));
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        return null;
                    }
                    return result;
                });
        if (query != null) {
            System.out.println(Arrays.toString(query.toArray()));
        } else {
            System.out.println("查询失败！");
        }
    }

}
