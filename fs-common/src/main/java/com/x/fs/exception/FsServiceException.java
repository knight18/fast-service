package com.x.fs.exception;

/**
 * 自定义API异常
 *
 * @author X
 * @date 2022/3/13
 */
public class FsServiceException extends RuntimeException {
    private Integer returnCode;
    private String errorCode;
    private String errorMessage;

    public FsServiceException(Integer returnCode, String errorCode, String errorMessage) {
        super();
        this.returnCode = returnCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public FsServiceException(String message) {
        super(message);
    }

    public FsServiceException(Throwable cause) {
        super(cause);
    }

    public FsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
