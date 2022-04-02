package com.x.fs.user.feignservice;

import com.x.fs.cache.api.bean.FsSysDictDTO;
import com.x.fs.cache.api.bean.FsSysDictInputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 在该类中，调用fs-cache服务的接口
 * @author x
 */
@FeignClient("fs-cache")
public interface DictInfoService {
    @PostMapping("/dict/getDictInfo")
    FsSysDictDTO getDictInfo(FsSysDictInputDTO inputDTO);

}
