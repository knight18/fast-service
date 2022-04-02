package com.x.fs.cache.server.scheduling;

import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.cache.server.config.CacheProperties;
import com.x.fs.cache.server.support.service.impl.FsSysDictCache;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务执行
 * @author x
 */
@Slf4j
@Component
public class CacheJob {

    @XxlJob("sysDictJobHandler")
    public ReturnT<String> sysDictJobHandler() throws Exception{
        String param = XxlJobHelper.getJobParam();
        boolean enableCache = FsApplicationContext.getBean(CacheProperties.class).getAllEnabled();
        if(!enableCache){
            log.info("字典缓存功能未开启，不必执行该定时任务");
            XxlJobHelper.log("字典缓存功能未开启，不必执行该定时任务");
        }
        log.info("字典缓存定时刷新功能开始"+param);
        XxlJobHelper.log("字典缓存定时刷新功能开始{}",param);
        FsSysDictCache fsSysDictCache = FsApplicationContext.getBean(FsSysDictCache.class);
        fsSysDictCache.refresh();
        log.info("字典缓存定时刷新功能完成"+param);
        XxlJobHelper.log("字典缓存定时刷新功能完成{}",param);
        return ReturnT.SUCCESS;
    }

}
