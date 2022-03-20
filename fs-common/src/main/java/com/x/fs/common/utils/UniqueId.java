package com.x.fs.common.utils;

public class UniqueId {
    private static final long START_TIME = 148320000000000L;
    private static final int APP_HOST_ID_BITS = 13;
    private static final int SEQUENCE_BITS  = 10;
    private static final long MAX_APP_HOST_ID = 8191;
    private static final long MAX = 1023L;
    private static final long APP_HOST_ID_SHIFT = 10;
    private static final long TIMESTAMP_LEFT_SHIFT = 23L;
    private long appHostId;
    private long lastTime = -1L;
    private long sequence = 0L;
    private static volatile UniqueId idGen = null;

    public static UniqueId getInstance(){
        if(idGen == null){
            Class var0 = UniqueId.class;
            synchronized (UniqueId.class){
                if(idGen == null){
                    idGen = new UniqueId(1L);
                }
            }
        }
        return idGen;
    }

    private UniqueId(long appHostId){
        if(appHostId > 8191L){
            throw new IllegalArgumentException("app host Id wrong: %d"+appHostId);

        }else {
            this.appHostId = appHostId;
        }
    }

    public Long nextId(){
        //原程序，在数据量特别大时，会出现返回-1情况，同时插入数据库时，会报主键冲突
        // return this.genUniqueId();

        //改为如下，i其实可以不用 预防死循环
        Long a = 0L;
        int i = 0;
        do {
            a = this.genUniqueId();
            i++;
            if(i>5){
                break;
            }
        }while (a == -1L);
        return a;
    }

    private synchronized long genUniqueId(){
        long current = System.currentTimeMillis();
        if(current < this.lastTime){
            return -1L;
        }else{
            if(current == this.lastTime){
                this.sequence = this.sequence + 1L & 1023L;
                if(this.sequence == 1023L){
                    current = this.nextMs(this.lastTime);
                }
            }else{
                this.sequence = 0L;
            }
            this.lastTime = current;
            return current - 148320000000000L << 23| this.appHostId << 10 | this.sequence;
        }
    }

    private long nextMs(long timeStamp){
        long current;
        for(current = System.currentTimeMillis(); current <= timeStamp; current = System.currentTimeMillis()){

        }
        return current;
    }
}
