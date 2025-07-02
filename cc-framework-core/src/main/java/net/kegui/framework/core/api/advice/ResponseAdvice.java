package net.kegui.framework.core.api.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.kegui.framework.core.api.annotation.OriginalResponse;
import net.kegui.framework.core.api.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应处理增强器
 * <p>
 * 统一处理REST API的返回结果，将控制器返回的数据自动包装为CommonResult格式。
 * 如果响应已经是CommonResult类型，则不做处理；否则将其包装为CommonResult.success的结果。
 * </p>
 * <p>
 * 如需返回原始响应内容而不被包装，可在控制器方法或类上添加@OriginalResponse注解。
 * </p>
 * <p>
 * 优先级说明：
 * 后续如果要添加响应加密等Advice，若优先级高于该类则只加密data部分，
 * 若优先级低于该类则会加密整个处理后的结果。
 * </p>
 *
 * @since 2025-07-02
 */
@Order(1)
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ResponseAdvice.class);
    private final ObjectMapper objectMapper;

    public ResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        // 检查方法或类上是否有OriginalResponse注解，有则跳过处理
        return !(methodParameter.hasMethodAnnotation(OriginalResponse.class) ||
                methodParameter.getContainingClass().isAnnotationPresent(OriginalResponse.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, 
                                 Class<? extends HttpMessageConverter<?>> selectedConverterType, 
                                 ServerHttpRequest request, ServerHttpResponse response) {
        // 已经是CommonResult类型，不需要再包装
        if (body instanceof CommonResult) {
            return body;
        }

        // 特殊类型处理
        if (body instanceof String) {
            try {
                // String类型需要特殊处理，因为StringHttpMessageConverter不能处理CommonResult
                return objectMapper.writeValueAsString(CommonResult.success(body));
            } catch (JsonProcessingException e) {
                log.error("序列化String响应失败", e);
                throw new RuntimeException("响应处理异常", e);
            }
        }

        if (body instanceof byte[]) {
            try {
                // 二进制数据需要转换为字节数组
                return objectMapper.writeValueAsBytes(CommonResult.success(body));
            } catch (JsonProcessingException e) {
                log.error("序列化二进制响应失败", e);
                throw new RuntimeException("响应处理异常", e);
            }
        }

        // 其他类型直接包装返回
        return CommonResult.success(body);
    }
}
