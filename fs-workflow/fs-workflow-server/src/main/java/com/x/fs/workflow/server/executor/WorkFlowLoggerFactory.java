package com.x.fs.workflow.server.executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author x
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class WorkFlowLoggerFactory {

    @Value("${jdbc.defaultDataSourceId}")
    String defaultDataSourceId;

    public WorkFlowLogger CreateWorkUnitLogger(String wuGuid) {
        return new WorkFlowLogger(wuGuid, defaultDataSourceId);
    }
}
