package com.x.fs.base.datasource;

import com.x.fs.base.constant.DataSourceConstan;
import com.x.fs.base.context.ThreadContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * @author x
 */
@Slf4j
public final class DataSourceContextHolder extends ThreadContextHolder {
    private static final DataSourceContextHolder DATA_SOURCE_CONTEXT_HOLDER = new DataSourceContextHolder();

    private DataSourceContextHolder() {
    }

    public static String getDataSource() {
        Stack<String> stack = DATA_SOURCE_CONTEXT_HOLDER.get(DataSourceConstan.DATA_SOURCE);
        return null != stack && !stack.isEmpty() ? stack.peek() : null;
    }

    public static void setDataSource(String dataSource) {
        log.info("change to [{}] dataSource.", dataSource);
        if (null == DATA_SOURCE_CONTEXT_HOLDER.get(DataSourceConstan.DATA_SOURCE)) {
            DATA_SOURCE_CONTEXT_HOLDER.put("dataSource", new Stack());
        }

        Stack stack = DATA_SOURCE_CONTEXT_HOLDER.get(DataSourceConstan.DATA_SOURCE);
        stack.push(dataSource);
    }

    public static String remove() {
        Stack stack = DATA_SOURCE_CONTEXT_HOLDER.get(DataSourceConstan.DATA_SOURCE);
        if (stack == null) {
            log.debug("not set datasource");
            return null;
        } else {
            Object dataSource = stack.pop();
            log.debug("remove dataSource [{}].", dataSource);
            if (stack.isEmpty()) {
                DATA_SOURCE_CONTEXT_HOLDER.remove(DataSourceConstan.DATA_SOURCE);
                log.debug("clear dataSource.");
            }
            return dataSource.toString();
        }
    }
}
