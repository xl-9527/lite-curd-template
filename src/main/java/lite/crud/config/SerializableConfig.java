package lite.crud.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
@Configuration(proxyBeanMethods = false)
public class SerializableConfig {

    /**
     * json config
     */
    @Bean
    public ObjectMapper jacksonObjectMapper(final Jackson2ObjectMapperBuilder builder) {
        return getObjectMapper(builder);
    }

    public static ObjectMapper getObjectMapper(final Jackson2ObjectMapperBuilder builder) {
        final ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        objectMapper.registerModule(
                new SimpleModule()
                        .addSerializer(Long.class, new ToStringSerializer()) // long -> string
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        );

        // ignore null
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // ignore java type not exist
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // disable An unknown attribute was found to throw an exception
        objectMapper.disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
        // empty bean throw exception
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        return objectMapper;
    }
}
