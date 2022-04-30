package com.x.fs.base.config;

import com.x.fs.base.annotation.NotEmptyCondition;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author x
 */
public class PropertyNotEmptyCondition implements Condition {

    public PropertyNotEmptyCondition() {
    }

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> annotationAttributes =
                annotatedTypeMetadata.getAnnotationAttributes(NotEmptyCondition.class.getName());
        if (annotationAttributes == null) {
            return false;
        }
        Object propertyNameObj = annotationAttributes.get("value");
        if (propertyNameObj == null) {
            return false;
        }
        String propertyName = (String) propertyNameObj;
        Environment env = conditionContext.getEnvironment();
        return StringUtils.isNotEmpty(env.getProperty(propertyName)) ||
                StringUtils.isNotEmpty(env.getProperty(toDashedForm(propertyName)));
    }

    /**
     * DataObjectPropertyName: jdbc.defaultDataSourceId ->
     * jdbc.default-data-source-id 驼峰名称换位 -
     * @param name
     * @return String
     */
    static String toDashedForm(String name) {
        StringBuilder result = new StringBuilder();
        String replaced = name.replace('_', '-');
        for (int i = 0; i < replaced.length(); i++) {
            char ch = replaced.charAt(i);
            if (Character.isUpperCase(ch) && result.length() > 0 && result.charAt(result.length() - 1) != '-') {
                result.append('-');
            }
            result.append(Character.toLowerCase(ch));
        }
        return result.toString();
    }

}

