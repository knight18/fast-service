package com.x.fs.base.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 * @author x
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited
public @interface DataSource {

    /**
     * 名称。
     */
    String value();

}
