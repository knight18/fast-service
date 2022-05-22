package com.x.fs.base.datasource.utils;

import com.x.fs.base.config.MoreDataSourceProperties;
import com.x.fs.base.constant.DataSourceConstan;
import com.x.fs.base.datasource.DataSourceContextHolder;
import com.x.fs.base.datasource.utils.DataSourceDescUtils;
import com.x.fs.base.utils.FsApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据库连接管理工具
 * @author x
 */
@Slf4j
public final class DataSourceUtils {
    private DataSourceUtils() {
    }

    /**
     * 设置具体数据源
     * @param dataSourceId 数据源id
     */
    public static void set(String dataSourceId) {
        checkDataSourceIdValue(dataSourceId);

        String dataSourceName = DataSourceDescUtils.getDataSourceName(dataSourceId);
        DataSourceContextHolder.setDataSource(dataSourceName);
    }

    /**
     * 获取当前数据源
     * @return 当前数据源
     */
    public static DataSource get() {
        String dataSourceName = DataSourceContextHolder.getDataSource();
        if (StringUtils.isEmpty(dataSourceName)) {
            log.info("use default dataSource",dataSourceName);
            String dataSourceId = geDefaultDataSourceId();
            dataSourceName = DataSourceDescUtils.getDataSourceName(dataSourceId);
        }
        return get(dataSourceName);
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


    private static void checkDataSourceIdValue(String dataSourceId) {
        if (StringUtils.isEmpty(dataSourceId)) {
            throw new IllegalArgumentException("input dataSourceId can not null.");
        }
        MoreDataSourceProperties moreDataSourceProperties = FsApplicationContext.getBean(MoreDataSourceProperties.class);
        List<String> dataSourceIds = Arrays.stream(moreDataSourceProperties.getDataSources())
                .map(dataSourceProperties -> dataSourceProperties.getId()).collect(Collectors.toList());
        if (!dataSourceIds.contains(dataSourceId)) {
            throw new IllegalArgumentException("fs the dataSourceId:[" + dataSourceId + "] not exist");
        }
    }

    private static String geDefaultDataSourceId() {
        return FsApplicationContext.getBean(MoreDataSourceProperties.class).getDefaultDataSourceId();
    }


    /**
     * 获取实际使用的数据源
     * @param dataSourceId 数据源ID
     * @return 数据源
     */
    public static DataSource get(String dataSourceId) {
        Map<String, DataSource> stringDataSourceMap = FsApplicationContext.getBeansOfType(DataSource.class);
        String dataSourceName = DataSourceDescUtils.getDataSourceName(dataSourceId);
        DataSource dataSource = stringDataSourceMap.get(dataSourceName);
        if (dataSource == null) {
            throw new IllegalArgumentException("can not find [" + dataSourceId + "] dataSource.");
        }
        return dataSource;
    }
}

