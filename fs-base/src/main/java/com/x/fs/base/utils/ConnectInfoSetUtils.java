package com.x.fs.base.utils;

import com.x.fs.base.constant.FsBaseConstant;
import com.x.fs.base.dto.ConnectInfoDTO;
import com.x.fs.common.exception.FsServiceException;

/**
 * 提取服务器地址信息
 *
 * @author x
 */
public class ConnectInfoSetUtils {

    public static ConnectInfoDTO dealConnectInfo(String input,String regex) {
        ConnectInfoDTO connectInfo = new ConnectInfoDTO();
        int port;
        String[] split = input.split(regex);
        if (split.length != 4) {
            throw new FsServiceException("服务器地址信息配置格式错误[" + input + "]");
        }
        if (!FsBaseConstant.CONNECT_INFO_FORMAT.matcher(split[0].trim()).matches()) {
            throw new FsServiceException("IP地址配置不正确[" + split[0].trim() + "]");
        }
        try {
            port = Integer.parseInt(split[1].trim());
        } catch (Exception e) {
            throw new FsServiceException("端口配置错误[" + split[1].trim() + "]");
        }
        connectInfo.setHost(split[0].trim());
        connectInfo.setPort(port);
        connectInfo.setUserName(split[2].trim());
        connectInfo.setPassWord(split[3]);
        return connectInfo;
    }

    public static ConnectInfoDTO dealBackeupConnectInfo(String input, String regex) {
        ConnectInfoDTO connectInfo = new ConnectInfoDTO();
        String[] split = input.split(regex);
        if (split.length != 3) {
            throw new FsServiceException("服务器地址信息配置格式错误[" + input + "]");
        }
        if (!FsBaseConstant.CONNECT_INFO_FORMAT.matcher(split[0].trim()).matches()) {
            throw new FsServiceException("IP地址配置不正确[" + split[0].trim() + "]");
        }
        connectInfo.setHost(split[0].trim());
        connectInfo.setUserName(split[1].trim());
        connectInfo.setPassWord(split[2]);
        return connectInfo;
    }

}
