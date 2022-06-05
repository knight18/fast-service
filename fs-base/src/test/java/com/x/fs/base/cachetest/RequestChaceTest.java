package com.x.fs.base.cachetest;

import com.x.fs.base.cache.impl.RequestCache;
import com.x.fs.base.dto.ConnectInfoDTO;
import com.x.fs.base.utils.ReflectUtils;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RequestChaceTest {
    @Test
    public void test01() {
        RequestCache<String> cahce = new RequestCache<>("stringCache");
        cahce.putValue("key1", "value1");
        cahce.putValue("key2", "value2");
        System.out.println(cahce.getSize());
        System.out.println(cahce.getValue("key1"));
        cahce.removeValue("key1");
        System.out.println(cahce.getValue("key1"));

        RequestCache<ConnectInfoDTO> connectInfoDTORequestCache = new RequestCache<ConnectInfoDTO>("connectInfoDTORequestCache");
        connectInfoDTORequestCache.putValue("c1", ReflectUtils.newInstanceWithDefault(ConnectInfoDTO.class, "host", "127.0.0.0"));
        connectInfoDTORequestCache.putValue("c2", ReflectUtils.newInstanceWithDefault(ConnectInfoDTO.class, "port", 3306));
        System.out.println(connectInfoDTORequestCache.getValue("c1"));

        RequestCache<ConnectInfoDTO> requestCachecc = new RequestCache<>("cc");
        requestCachecc.putValue("c1", ReflectUtils.newInstanceWithDefault(ConnectInfoDTO.class));
        System.out.println(requestCachecc.getValue("c1"));

        RequestCache<List<ConnectInfoDTO>> rpc2spdParamCfgCache = new RequestCache<>("rpc2spdParamCfgCache1");
        ArrayList<ConnectInfoDTO> list = new ArrayList<>();
        list.add(ReflectUtils.newInstanceWithDefault(ConnectInfoDTO.class));
        rpc2spdParamCfgCache.putValue("c3", list);
        System.out.println(rpc2spdParamCfgCache.getValue("c3"));
    }

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(ConnectInfoDTO.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);
        });
    }


}
