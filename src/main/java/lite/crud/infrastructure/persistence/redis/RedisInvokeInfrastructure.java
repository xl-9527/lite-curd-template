package lite.crud.infrastructure.persistence.redis;

import lite.crud.infrastructure.InvokeInfrastructure;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/5
 */
@Service
public class RedisInvokeInfrastructure<T> implements InvokeInfrastructure<RedisTemplate<String, T>> {

    private final RedisTemplate<String, T> redisTemplate;

    public RedisInvokeInfrastructure(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public RedisTemplate<String, T> invoke() {
        return redisTemplate;
    }

    public HashOps<T> opsHash(final String key) {
        return new HashOps<>(redisTemplate, key);
    }

    /**
     * TODO 实现一个 LRU 缓存队列来存贮 Hash OPS
     */
    private static class LruSupport {

    }
}
