package lite.crud.config.mybatis;

import lite.crud.config.mybatis.intercept.PageIntercept;
import lite.crud.config.mybatis.intercept.PageResultIntercept;
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
    public ConfigurationCustomizer interceptorCustomer(final PageIntercept pageIntercept, final PageResultIntercept pageResultIntercept) {
        return configuration -> {
            configuration.addInterceptor(pageIntercept);
            configuration.addInterceptor(pageResultIntercept);
        };
    }
}
