package com.x.fs.cache.api.service;

import com.x.fs.cache.api.bean.FsSysDictDTO;
import com.x.fs.cache.api.bean.FsSysDictInputDTO;

/**
 * @author x
 */
public interface CacheApiService {

    /**
     * 获取fs_sys_dict表数据
     * @param inputDTO
     * @return
     */
    FsSysDictDTO getDictInfo(FsSysDictInputDTO inputDTO);


}
