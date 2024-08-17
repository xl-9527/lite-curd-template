package lite.crud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@MapperScan(basePackages = "lite.crud.infrastructure.persistence.mysql")
@Configuration(proxyBeanMethods = false)
public class MyBatisConfig {


}
