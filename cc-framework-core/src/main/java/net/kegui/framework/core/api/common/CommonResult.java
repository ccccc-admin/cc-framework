package net.kegui.framework.core.api.common;

import java.io.Serializable;

/**
 * 通用API返回结果封装类
 * <p>
 * 统一封装REST API的返回结果格式，包含状态码、消息和数据三部分。
 * 提供了各种静态工厂方法来创建不同状态的结果对象。
 * </p>
 *
 * @param <T> 数据类型泛型参数
 * @since 2025-07-02
 */
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private long code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 业务数据
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果（自定义消息）
     *
     * @param data 业务数据
     * @param message 自定义成功消息
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果（使用错误码对象）
     * 
     * @param errorCode 错误码对象
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果（自定义消息）
     * 
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 资源未找到结果（自定义消息）
     * 
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> not_found(String message) {
        return new CommonResult<>(ResultCode.NOT_FOUND.getCode(), message, null);
    }

    /**
     * 资源未找到结果
     * 
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> not_found() {
        return new CommonResult<>(ResultCode.NOT_FOUND.getCode(), ResultCode.NOT_FOUND.getMessage(), null);
    }

    /**
     * 通用失败返回结果
     * 
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     * 
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果（自定义消息）
     * 
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     * 
     * @param data 附加数据
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未登录返回结果（自定义消息）
     * 
     * @param message 错误消息
     * @param data 附加数据
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> unauthorized(String message, T data) {
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), message, data);
    }

    /**
     * 未授权返回结果
     * 
     * @param data 附加数据
     * @param <T> 数据类型
     * @return 通用结果对象
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
