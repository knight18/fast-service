package com.x.fs.cache.server.apiimpl;

import com.x.fs.cache.api.bean.FsSysDictDTO;
import com.x.fs.cache.api.bean.FsSysDictInputDTO;
import com.x.fs.cache.api.service.CacheApiService;
import com.x.fs.cache.server.support.service.impl.FsSysDictService;
import com.x.fs.common.exception.FsServiceException;
import com.x.fs.mbg.model.FsSysDict;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author x
 */
@Controller
@Slf4j
@RequestMapping("/dict")
@Service
public class CacheApiServiceImpl implements CacheApiService {

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/getDictInfo", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public FsSysDictDTO getDictInfo(@RequestBody FsSysDictInputDTO inputDTO) {
        if(inputDTO == null || StringUtils.isBlank(inputDTO.getDictNo()) || StringUtils.isBlank(inputDTO.getKey())){
            throw new FsServiceException("dictNo,key字段值，不能同时都为null");
        }
        FsSysDictDTO result = new FsSysDictDTO();
        FsSysDict sysDict = FsSysDictService.getDictInfo(inputDTO.getDictNo(),inputDTO.getKey());
        if(sysDict != null){
            BeanUtils.copyProperties(sysDict,result);
        }
        return result;
    }
}
