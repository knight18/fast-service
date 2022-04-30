package com.x.fs.database.server.atom.impl;

import com.x.fs.database.server.atom.RegistryInfoAtom;
import com.x.fs.mbg.mapper.RegistryInfoMapper;
import com.x.fs.mbg.model.RegistryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author x
 */
@Component
public class RegistryInfoAtomImpl implements RegistryInfoAtom {

    @Autowired
    private RegistryInfoMapper registryInfoMapper;

    @Override
    public RegistryInfo getRegistryInfoByKey(String key) {
        return registryInfoMapper.selectByPrimaryKey(key);
    }

    @Override
    public int insertRegistryInfo(RegistryInfo input) {
        return registryInfoMapper.insert(input);
    }
}
