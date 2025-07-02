package net.kegui.framework.core.api.common;

/**
 * 错误码接口
 * <p>
 * 定义系统中所有错误码的通用接口。所有自定义的错误码枚举类应实现此接口。
 * </p>
 *
 * @since 2025-07-02
 */
public interface IErrorCode {
    /**
     * 获取错误状态码
     *
     * @return 数字类型的错误码
     */
    long getCode();

    /**
     * 获取错误消息
     *
     * @return 可读的错误描述信息
     */
    String getMessage();
}

