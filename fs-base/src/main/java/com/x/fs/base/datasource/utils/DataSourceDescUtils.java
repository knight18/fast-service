package com.x.fs.base.datasource.utils;

import com.x.fs.base.constant.DataSourceConstan;
import org.apache.commons.lang.StringUtils;

/**
 * @author x
 */
public class DataSourceDescUtils {

    public DataSourceDescUtils(){

    }

    /**
     * 获取数据源beanName
     * @param dataSourceId 数据源ID
     * @return beanName
     */
    public static String getDataSourceName(String dataSourceId) {
        if (!StringUtils.isEmpty(dataSourceId) && dataSourceId.endsWith(DataSourceConstan.DATA_SOURCE_SUFFIX) &&
                dataSourceId.length() > DataSourceConstan.DATA_SOURCE_SUFFIX.length()) {
            return dataSourceId;
        } else {
            return dataSourceId + DataSourceConstan.DATA_SOURCE_SUFFIX;
        }
    }

    /**
     * 获取数据源beanName前缀
     * @param dataSourceId 数据源ID
     * @return beanname前缀
     */
    public static String getDataSourceNamePrefix(String dataSourceId) {
        String dataDourceName = getDataSourceName(dataSourceId);
        return dataDourceName.substring(0, dataDourceName.length() - DataSourceConstan.DATA_SOURCE_SUFFIX.length());
    }

    /**
     * 获取数据源事务管理器beanname
     * @param dataSourceId 数据源ID
     * @return beanName
     */
    public static String getTransManagerName(String dataSourceId) {
        String dataSourceNamePrefix = getDataSourceNamePrefix(dataSourceId);
        return dataSourceNamePrefix + DataSourceConstan.TRANSACTION_MANAGER_SUFFIX;
    }

    /**
     * 获取数据源sqlsessionFactory beanName
     * @param dataSourceId 数据源ID
     * @return beanName
     */
    public static String getSqlSessionFactoryName(String dataSourceId) {
        String dataSourceNamePrefix = getDataSourceNamePrefix(dataSourceId);
        return dataSourceNamePrefix + DataSourceConstan.SQL_SESSION_FACTORY_SUFFIX;
    }

}
