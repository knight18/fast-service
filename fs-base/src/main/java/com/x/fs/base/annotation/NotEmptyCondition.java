package com.x.fs.base.annotation;

import com.x.fs.base.config.PropertyNotEmptyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author x
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(PropertyNotEmptyCondition.class)
public @interface NotEmptyCondition {

    /**
     * 要判断的属性.
     */
    String value();
}