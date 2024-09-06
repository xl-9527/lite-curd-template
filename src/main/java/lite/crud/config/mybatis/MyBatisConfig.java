package lite.crud.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@MapperScan(basePackages = "lite.crud.infrastructure.persistence.mysql")
@Configuration(proxyBeanMethods = false)
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(final PageIntercept pageIntercept) {
        return configuration -> configuration.addInterceptor(pageIntercept);
    }
}
