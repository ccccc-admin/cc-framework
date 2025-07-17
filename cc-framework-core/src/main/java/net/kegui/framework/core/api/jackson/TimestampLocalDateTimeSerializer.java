package net.kegui.framework.core.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * LocalDateTime转时间戳序列化器
 * 用于将LocalDateTime类型转换为时间戳返回给前端
 */
public class TimestampLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null) {
            long timestamp = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            jsonGenerator.writeNumber(timestamp);
        } else {
            jsonGenerator.writeNull();
        }
    }
}
