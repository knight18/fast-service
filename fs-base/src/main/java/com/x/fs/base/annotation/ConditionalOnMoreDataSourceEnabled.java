package com.x.fs.base.annotation;

import java.lang.annotation.*;

/**
 * @author x
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@NotEmptyCondition("jdbc.default-data-source-id")
public @interface ConditionalOnMoreDataSourceEnabled {
}