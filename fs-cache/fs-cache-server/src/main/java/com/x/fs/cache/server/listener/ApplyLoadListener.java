package com.x.fs.cache.server.listener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.x.fs.cache.server.service.ApplyactionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;

/**
 * 缓存数据监听器
 * @author x
 */
@Slf4j
public class ApplyLoadListener implements SmartLifecycle, ApplicationListener {

    private final ApplicationContext context;

    private final AtomicBoolean RUN_STATUS = new AtomicBoolean(false);

    public ApplyLoadListener(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }

    @Override
    public void start() {
        contextLoaded(context);
        this.RUN_STATUS.set(true);
    }

    @Override
    public void stop() {
        this.RUN_STATUS.set(false);
    }

    @Override
    public int getPhase() {
        return -1;
    }

    @Override
    public boolean isRunning() {
        return RUN_STATUS.get();
    }

    @Override
    public boolean isAutoStartup() {
        return SmartLifecycle.super.isAutoStartup();
    }

    @Override
    public void stop(Runnable callback) {
        SmartLifecycle.super.stop(callback);
    }


    /**
     * 加载数据
     * @param context context
     */
    public void contextLoaded(ApplicationContext context) {
        String[] initDataNames = context.getBeanNamesForType(ApplyactionLoader.class);
        log.info("缓存系统需要初始化的数据共[" + initDataNames.length + "]条");
        if (initDataNames.length > 0) {
            List<ApplyactionLoader> initDataList = new ArrayList<>();
            for (String initData : initDataNames) {
                if (log.isDebugEnabled()) {
                    log.info("初始化的beanName为:[{}]", initData);
                }
                try {
                    ApplyactionLoader applyInit = context.getBean(initData, ApplyactionLoader.class);
                    initDataList.add(applyInit);
                } catch (Exception e) {
                    log.error(MessageFormat.format("Spring容器中查找bean[{0}]数据错误,错误信息是:[{1}]", initData, e.getMessage()), e.getCause());
                    System.exit(1);
                }
            }
            sortInit(initDataList);
            log.info("系统初始化开始......");
            for (ApplyactionLoader appData : initDataList) {
                try {
                    appData.load();
                } catch (Exception e) {
                    log.error("应用初始化异常,异常信息为:[{0}]", e);
                    log.error(MessageFormat.format("系统初始化错误信息:[{0}]，原因是:[{}]", e.getMessage()), e.getCause());
                    System.exit(1);
                }
            }
            log.info("系统初始化完成......");
        }
    }

    /**
     * 根据优先级排序，初始化列表
     * @param initDataList 排序List
     */
    protected void sortInit(List<ApplyactionLoader> initDataList) {
        initDataList.sort((l, s) -> {
            if (l.getOrder() > s.getOrder()) {
                return 1;
            } else if (l.getOrder() < s.getOrder()) {
                return -1;
            }
            return 0;
        });
    }
}
