package net.kegui.framework.core.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记原始响应注解
 * <p>
 * 添加到控制器方法或类上，被标记的方法或类的响应结果将不会被ResponseAdvice自动包装成CommonResult格式，
 * 而是直接返回原始响应内容。用于需要返回特定格式的API接口，如文件下载、第三方回调等场景。
 * </p>
 * 
 * @see net.kegui.framework.core.api.advice.ResponseAdvice
 * @since 2025-07-02
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalResponse {
}
