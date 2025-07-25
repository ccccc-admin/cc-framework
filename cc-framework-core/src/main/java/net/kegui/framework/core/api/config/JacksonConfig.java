package net.kegui.framework.core.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.kegui.framework.core.api.jackson.TimestampLocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;

/**
 * Jackson全局配置
 */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 使用自定义的LocalDateTime序列化器和反序列化器
        SimpleModule timestampModule = new SimpleModule();
        timestampModule.addSerializer(LocalDateTime.class, new TimestampLocalDateTimeSerializer());

        objectMapper.registerModule(new JavaTimeModule());
        // 注册模块
        objectMapper.registerModule(timestampModule);

        return objectMapper;
    }
}
