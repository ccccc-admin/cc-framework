package net.kegui.framework.core.api.config;

import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.util.List;

@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 显式定义静态资源路径（替代默认 /**
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        // 新增 favicon 专用映射（策略隔離）
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/favicon.ico")
                .setCachePeriod(86400) // 24小时缓存（秒）
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) {
                        // 确保仅在路径完全匹配时返回资源
                        return location.exists() ? location : null;
                    }
                });
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 将JSON转换器放在前面，提高优先级
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

}
