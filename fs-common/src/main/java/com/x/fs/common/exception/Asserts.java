package com.x.fs.common.exception;


/**
 * 断言处理类，用于抛出各种API异常
 *
 * @author x
 */
public class Asserts {
    public static void fail(String message) {
        throw new FsServiceException(message);
    }

    public static void fail(Integer returnCode, String errorCode, String errorMessage) {
        throw new FsServiceException(errorCode);
    }
}
