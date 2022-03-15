package com.x.fs.user.atom;

import com.x.fs.model.FsSysUser;

/**
 * @author x
 */
public interface FsUserServiceAtom {

    FsSysUser loadUserByUserCode(String userCode);

}
