package com.x.fs.user.service.impl;

import com.x.fs.dto.RequestDTO;
import com.x.fs.exception.FsServiceException;
import com.x.fs.model.FsSysUser;
import com.x.fs.user.atom.FsUserServiceAtom;
import com.x.fs.user.dto.UserInfoDTO;
import com.x.fs.user.service.FsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author x
 */
@Service
public class FsUserServiceImpl implements FsUserService {

    @Autowired
    private FsUserServiceAtom fsUserServiceAtom;

    @Override
    public FsSysUser login(RequestDTO<UserInfoDTO> input) {
        UserInfoDTO user = input.getParams();
        FsSysUser fsSysUser = fsUserServiceAtom.loadUserByUserCode(user.getUserCode());
        if(fsSysUser == null || fsSysUser.getUserPwd() != null || !fsSysUser.getUserPwd().equals(user.getPassword())){
          throw new FsServiceException("账户或密码错误，请重新输入");
        }
        return fsSysUser;
    }

    @Override
    public FsSysUser loadUserByUserCode(FsSysUser input) {
        return null;
    }
}
