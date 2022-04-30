package com.x.fs.base.aop;


import com.x.fs.base.annotation.DataSource;
import com.x.fs.base.datasource.DataSourceContextHolder;
import com.x.fs.base.datasource.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author x
 */
@Order(-1)
@Aspect
@Slf4j
public class DataSourceSetAspect {

    public DataSourceSetAspect() {
    }

    @Before("@annotation(com.x.fs.base.annotation.DataSource) || @within(com.x.fs.base.annotation.DataSource)")
    private void before(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method m = methodSignature.getMethod();
        Class clazz = methodSignature.getDeclaringType();
        try {
            DataSource annotation = null;
            if (m.isAnnotationPresent(DataSource.class)) {
                annotation = (DataSource) m.getAnnotation(DataSource.class);
            } else if (clazz.isAnnotationPresent(DataSource.class)) {
                annotation = (DataSource) clazz.getAnnotation(DataSource.class);
            }
            if (null != annotation) {
                String dataSourceName = DataSourceUtils.getDataSourceName(annotation.value());
                DataSourceContextHolder.setDataSource(dataSourceName);
                log.debug("dataSource [{}] set into DataSourceContextHolder in DataSourceSetAspect", dataSourceName);
            }
        } catch (Exception var7) {
            log.debug("DataSourceSetAspect in change dataSource error. {}", var7.getMessage(), var7.getCause());
        }
    }

    @After("@annotation(com.x.fs.base.annotation.DataSource) || @within(com.x.fs.base.annotation.DataSource)")
    private void clearDataSource() {
        DataSourceContextHolder.remove();
    }
}
