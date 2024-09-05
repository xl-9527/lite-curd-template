package lite.crud.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lite.crud.config.security.SysGrantedAuthority;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xl-9527
 * @since 2024/9/5
 */
@SpringBootTest
class SerializableConfigTest {

    private final Logger log = LoggerFactory.getLogger(SerializableConfigTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getObjectMapper() throws JsonProcessingException {
        // 非无参构造器注入配置
        final SysGrantedAuthority simpleGrantedAuthority = objectMapper.readValue("{\"role\": \"ROLE_ADMIN\", \"authority\": \"ROLE_ADMIN\"}", SysGrantedAuthority.class);
        log.info("{}", simpleGrantedAuthority);
    }
}
