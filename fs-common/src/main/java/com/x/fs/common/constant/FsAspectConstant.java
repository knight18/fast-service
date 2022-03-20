package com.x.fs.common.constant;

public interface FsAspectConstant {

//    String WEB_LOG_CUT = "execution(public * com.x.fs.*.controller.*.*(..))||execution(public * com.x.*.*.controller.*.*(..))";
    String WEB_LOG_CUT = "execution(public * com.x.fs.user.controller.*.*(..))";

    String REQUEST_ID = "requestId";
}
