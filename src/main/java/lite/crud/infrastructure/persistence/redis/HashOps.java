package lite.crud.infrastructure.persistence.redis;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * @author xl-9527
 * @since 2024/9/5
 */
public class HashOps<T> implements Map<String, T> {

    private final HashOperations<String, String, T> opsForHash;
    private final RedisTemplate<String, T> redisTemplate;
    private final String key;

    public HashOps(final RedisTemplate<String, T> redisTemplate, final String key) {
        this.redisTemplate = redisTemplate;
        this.opsForHash = redisTemplate.opsForHash();
        this.key = key;
    }

    @Override
    public int size() {
        return opsForHash.size(key).intValue();
    }

    @Override
    public boolean isEmpty() {
        return opsForHash.size(key) == 0;
    }

    @Override
    public boolean containsKey(final Object field) {
        return opsForHash.hasKey(key, field);
    }

    @Override
    public boolean containsValue(final Object value) {
        final List<T> values = opsForHash.values(key);
        for (final T t : values) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(final Object field) {
        return opsForHash.get(key, field);
    }

    @Override
    public T put(final String field, final T value) {
        opsForHash.put(key, field, value);
        return value;
    }

    @Override
    public T remove(final Object field) {
        final T t = this.get(field);
        if (ObjectUtils.isNotEmpty(t)) {
            opsForHash.delete(key, field);
            return t;
        }
        return t;
    }

    @Override
    public void putAll(@NonNull final Map<? extends String, ? extends T> m) {
        opsForHash.putAll(key, m);
    }

    @Override
    public void clear() {
        redisTemplate.delete(key);
    }

    @Override
    public Set<String> keySet() {
        return opsForHash.keys(key);
    }

    @Override
    public Collection<T> values() {
        return opsForHash.values(key);
    }

    @Override
    public Set<Entry<String, T>> entrySet() {
        return opsForHash.entries(key).entrySet();
    }
}
