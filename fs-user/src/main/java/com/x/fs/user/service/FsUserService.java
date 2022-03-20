package com.x.fs.user.service;

import com.x.fs.common.dto.RequestDTO;
import com.x.fs.mbg.model.FsSysUser;
import com.x.fs.user.dto.UserInfoDTO;

/**
 * @author x
 */
public interface FsUserService {

    /**
     * 登录功能
     *
     * @param user
     * @return 生成的JWT的token
     */
    FsSysUser login(RequestDTO<UserInfoDTO> user);

    FsSysUser loadUserByUserCode(FsSysUser input);

}
