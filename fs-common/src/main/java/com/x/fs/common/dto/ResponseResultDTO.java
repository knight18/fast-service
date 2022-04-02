package com.x.fs.common.dto;

/**
 * @author x
 */
public class ResponseResultDTO<T> {
    private T data;
    private String message;
    private Integer code;

    public ResponseResultDTO() {
    }

    public ResponseResultDTO(Integer code, String message, T data) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseResultDTO<T> success(T data) {
        return new ResponseResultDTO<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResponseResultDTO<T> success(T data, String message) {
        return new ResponseResultDTO<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> ResponseResultDTO<T> failed(IErrorCode errorCode) {
        return new ResponseResultDTO<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> ResponseResultDTO<T> failed(IErrorCode errorCode, String message) {
        return new ResponseResultDTO<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseResultDTO<T> failed(String message) {
        return new ResponseResultDTO<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponseResultDTO<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResponseResultDTO<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseResultDTO<T> validateFailed(String message) {
        return new ResponseResultDTO<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResponseResultDTO<T> unauthorized(T data) {
        return new ResponseResultDTO<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResponseResultDTO<T> forbidden(T data) {
        return new ResponseResultDTO<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public ResponseResultDTO(String message, Integer code) {
        this(code, message,null );
    }

    public ResponseResultDTO(T data) {
        this(200, "操作成功",data );
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
