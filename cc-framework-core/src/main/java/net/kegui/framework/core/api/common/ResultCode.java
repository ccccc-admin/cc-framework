package net.kegui.framework.core.api.common;

/**
 * 通用结果状态码枚举
 * <p>
 * 定义系统中常用的HTTP状态码及对应的业务含义。
 * 可以在业务模块中扩展更多的状态码枚举类。
 * </p>
 *
 * @since 2025-07-02
 */
public enum ResultCode implements IErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败，服务器内部错误
     */
    FAILED(500, "操作失败"),

    /**
     * 请求的资源不存在
     */
    NOT_FOUND(404, "资源不存在"),

    /**
     * 参数校验失败
     */
    VALIDATE_FAILED(400, "参数校验失败"),

    /**
     * 未授权，未登录或token已过期
     */
    UNAUTHORIZED(401, "暂未登录或token已过期"),

    /**
     * 禁止访问，没有操作权限
     */
    FORBIDDEN(403, "没有访问权限");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}