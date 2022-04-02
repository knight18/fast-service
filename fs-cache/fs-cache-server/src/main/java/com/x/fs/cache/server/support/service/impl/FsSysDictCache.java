package com.x.fs.cache.server.support.service.impl;

import com.x.fs.cache.server.constant.CacheConstant;
import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.cache.server.support.service.FsCacheSysCache;
import com.x.fs.mbg.mapper.FsSysDictMapper;
import com.x.fs.mbg.model.FsSysDict;
import com.x.fs.mbg.model.FsSysDictExample;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * fs_sys_dict表数据缓存，加载类，不对外暴露
 * @author x
 */
@Component
public class FsSysDictCache extends FsCacheSysCache<String, List<FsSysDict>> {

    @Override
    public String getCacheName() {
        return CacheConstant.CACHE_DICT;
    }

    /**
     * 加载缓存数据来源
     * @return map
     */
    @Override
    protected Map<String, List<FsSysDict>> load() {
        Map<String,List<FsSysDict>> map = new HashMap<>();
        List<FsSysDict> list =  FsApplicationContext.getBean(FsSysDictMapper.class).selectByExample(new FsSysDictExample());
        if(!StringUtils.isEmpty(list)){
            List<FsSysDict> dict = new ArrayList<>();
            Set<String> set = new HashSet<>();
            list.forEach(v ->{
                if(set.add(v.getDictKey())){
                    dict.add(v);
                    map.put(v.getDictNo(),dict);
                }
            });
        }
        return map;
    }

    @Override
    public List<FsSysDict> loadItem(String key) {
        if (key == null) {
            return null;
        }
        List<FsSysDict> fsSysDictList;
        FsSysDictExample fsSysDictExample = new FsSysDictExample();
        FsSysDictExample.Criteria criteria = fsSysDictExample.createCriteria();
        criteria.andDictNoEqualTo(key);
        fsSysDictList = FsApplicationContext.getBean(FsSysDictMapper.class).selectByExample(fsSysDictExample);
        return fsSysDictList;
    }
}
