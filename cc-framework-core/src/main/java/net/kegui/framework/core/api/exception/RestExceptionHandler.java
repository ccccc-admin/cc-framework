package net.kegui.framework.core.api.exception;

import cn.dev33.satoken.exception.NotLoginException;
import jakarta.servlet.ServletException;
import net.kegui.framework.core.api.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * REST API 全局异常处理器
 * 
 * @since 2025-07-02
 */
@Order(2)
@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 默认全局异常处理，处理未被其他异常处理器捕获的异常
     *
     * @param e 异常对象
     * @return 统一封装的响应结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<String> exception(Exception e) {
        log.error("全局异常信息: {}", e.getMessage(), e);
        return CommonResult.failed(e.getMessage());
    }

    /**
     * 处理参数校验失败异常
     *
     * @param e 参数校验异常
     * @return 统一封装的响应结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError() != null ? 
                         e.getBindingResult().getFieldError().getDefaultMessage() : 
                         "参数验证失败";
        log.warn("参数校验失败: {}", message);
        return CommonResult.validateFailed(message);
    }

    /**
     * 处理资源未找到异常
     *
     * @param e 404相关异常
     * @return 统一封装的响应结果
     */
    @ExceptionHandler({NoHandlerFoundException.class, ServletException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult<String> noHandlerFoundException(Exception e) {
        log.warn("资源未找到: {}", e.getMessage());
        return CommonResult.not_found();
    }

    /**
     * 未登录访问需鉴权的资源
     *
     * @Param e
     * @return 统一封装的响应结果
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult<String> unauthorized(NotLoginException e){
        log.warn("未登录: {}", e.getMessage());
        return CommonResult.unauthorized(e.getMessage(),null);
    }
}
