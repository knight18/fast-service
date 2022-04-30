package com.x.fs.base.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.x.fs.base.annotation.ConditionalOnMoreDataSourceEnabled;
import com.x.fs.base.aop.DataSourceSetAspect;
import com.x.fs.base.datasource.DataSourceUtils;
import com.x.fs.base.datasource.MoreDataSourceRegistrar;
import com.x.fs.base.utils.FsApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import com.x.fs.base.config.MoreDataSourceProperties.DataSourceProperties;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置文件
 * @author x
 */
@ConditionalOnMoreDataSourceEnabled
@AutoConfigureOrder(-1)
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({MoreDataSourceProperties.class})
@Import({MoreDataSourceRegistrar.class})
public class MoreDataSourceAutoConfig {
    @Autowired
    private MoreDataSourceProperties kocaMultiDataSource;

    public MoreDataSourceAutoConfig() {
    }

    @Bean
    public DataSourceSetAspect dataSourceSetAspect() {
        return new DataSourceSetAspect();
    }

    @Bean
    public MoreDataSourceAutoConfig.DruidFilterBeanPostProcessor druidFilterBeanPostProcessor() {
        Map<String, DataSourceProperties> map = new HashMap();
        DataSourceProperties[] dataSources = this.kocaMultiDataSource.getDataSources();
        for(int i = 0; i < dataSources.length; ++i) {
            DataSourceProperties dataSource = dataSources[i];
            String dataSourceId = dataSource.getId();
            String dataSourceName = DataSourceUtils.getDataSourceName(dataSourceId);
            map.put(dataSourceName, dataSource);
        }
        return new MoreDataSourceAutoConfig.DruidFilterBeanPostProcessor(map);
    }

    @Bean
    @ConditionalOnMissingBean({FsApplicationContext.class})
    public FsApplicationContext appContext() {
        return new FsApplicationContext();
    }

    static class DruidFilterBeanPostProcessor implements BeanPostProcessor {
        private Map<String, DataSourceProperties> dataSourcePropertiesMap;
        DruidFilterBeanPostProcessor(Map<String, DataSourceProperties> dataSourcePropertiesMap) {
            this.dataSourcePropertiesMap = dataSourcePropertiesMap;
        }
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (this.dataSourcePropertiesMap.containsKey(beanName) && bean instanceof DruidDataSource) {
                try {
                    ((DruidDataSource)bean).setFilters((String)(this.dataSourcePropertiesMap.get(beanName)).getPool().get("druidFilters"));
                } catch (SQLException var4) {
                    throw new BeanInitializationException("Init druid dataBase filter failed", var4);
                }
            }
            return bean;
        }
    }
}
