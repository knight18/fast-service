package com.x.fs.database.server.atom;

import com.x.fs.mbg.model.RegistryInfo;

/**
 * @author x
 */
public interface RegistryInfoAtom {

    RegistryInfo getRegistryInfoByKey(String key);


    int insertRegistryInfo(RegistryInfo input);

}
