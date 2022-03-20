package com.x.fs.user.atom;

import com.x.fs.mbg.model.FsSysUser;

/**
 * @author x
 */
public interface FsUserServiceAtom {

    FsSysUser loadUserByUserCode(String userCode);

}
