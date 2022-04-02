package com.x.fs.user.controller;

import com.x.fs.base.utils.DateUtils;
import com.x.fs.common.dto.RequestDTO;
import com.x.fs.common.dto.ResponseResultDTO;
import com.x.fs.mbg.model.FsSysUser;
import com.x.fs.user.dto.UserInfoDTO;
import com.x.fs.user.service.FsUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录相关接口
 * @author x
 */
@Controller
@Slf4j
@RequestMapping("/userinfo")
public class FsUserController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FsUserService fsUserService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResultDTO login(@RequestBody RequestDTO<UserInfoDTO> user) {
        log.info("用户已登录！！！！！！");
        FsSysUser sysUser = fsUserService.login(user);

        int date = DateUtils.getSysDate();
        if (sysUser == null) {
            return ResponseResultDTO.validateFailed("用户名或密码错误");
        }
        return ResponseResultDTO.success(sysUser);
    }



}
