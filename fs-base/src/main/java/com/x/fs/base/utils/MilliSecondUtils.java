package com.x.fs.base.utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 如果频繁使用系统当前时间，使用该静态方法
 * @author x
 */
public class MilliSecondUtils {

    private static volatile long current;

    static {
        new MilliSecondUtils();
    }

    private MilliSecondUtils() {
        current = System.currentTimeMillis();
        scheduledExecutorService();
    }

    private void scheduledExecutorService() {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
        Thread timeThread = new Thread(new TimeWorker());
        timeThread.setDaemon(true);
        pool.scheduleAtFixedRate(timeThread, 1, 1, TimeUnit.MILLISECONDS);
    }

    public static long currentTimeMillis() {
        return current;
    }

    class TimeWorker implements Runnable{
        @Override
        public void run() {
            current = System.currentTimeMillis();
        }

    }



}
