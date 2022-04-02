package com.x.fs.cache.server.service;

/**
 * 缓存加载数据的顶层接口
 * @author x
 */
public interface ApplyactionLoader {

   /**
    * 缓存初始化，加载数据，实现该接口
    */
   void load();

   /**
    * 获取优先级，根据这个排序，加载顺序，默认5
    * @return
    */
   int getOrder();

}
