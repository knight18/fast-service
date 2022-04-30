package com.x.fs.base.datasource;

import com.x.fs.base.constant.DataSourceConstan;
import org.apache.commons.lang.StringUtils;

/**
 * 数据库连接管理工具
 * @author x
 */
public final class DataSourceUtils {
    private DataSourceUtils() {
    }

    public static String getDataSourceName(String dataSourceId) {
        return !StringUtils.isEmpty(dataSourceId) && dataSourceId.endsWith(DataSourceConstan.DATA_SOURCE_SUFFIX) && dataSourceId.length() > DataSourceConstan.DATA_SOURCE_SUFFIX.length() ? dataSourceId : dataSourceId + DataSourceConstan.DATA_SOURCE_SUFFIX;
    }

    public static String getDataSourceNamePrefix(String dataSourceId) {
        String dataDourceName = getDataSourceName(dataSourceId);
        return dataDourceName.substring(0, dataDourceName.length() - DataSourceConstan.DATA_SOURCE_SUFFIX.length());
    }

    public static String getTransManagerName(String dataSourceId) {
        String dataSourceNamePrefix = getDataSourceNamePrefix(dataSourceId);
        return dataSourceNamePrefix + DataSourceConstan.TRANSACTION_MANAGER_SUFFIX;
    }

    public static String getSqlSessionFactoryName(String dataSourceId) {
        String dataSourceNamePrefix = getDataSourceNamePrefix(dataSourceId);
        return dataSourceNamePrefix + DataSourceConstan.SQL_SESSION_FACTORY_SUFFIX;
    }
}

