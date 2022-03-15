package com.x.fs.user.atom.impl;

import com.x.fs.mapper.FsSysUserMapper;
import com.x.fs.model.FsSysUser;
import com.x.fs.user.atom.FsUserServiceAtom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author x
 */
@Component
public class FsUserServiceAtomImpl implements FsUserServiceAtom {

    @Autowired
    private FsSysUserMapper fsSysUserMapper;

    @Override
    public FsSysUser loadUserByUserCode(String userCode) {
        return fsSysUserMapper.selectByPrimaryKey(userCode);
    }
}
