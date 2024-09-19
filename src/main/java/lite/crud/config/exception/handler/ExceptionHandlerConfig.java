package lite.crud.config.exception.handler;

import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
public class ExceptionHandlerConfig {

    private static final Map<Class<? extends Exception>, ExceptionResolveHandler<Exception>> EXCEPTION_MAP = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static void register(final Exception exception, final ExceptionResolveHandler handler) {
        EXCEPTION_MAP.put(exception.getClass(), handler);
    }


    public static String getMsg(final Exception exception) {
        final ExceptionResolveHandler<Exception> handler = EXCEPTION_MAP.get(exception.getClass());

        if (ObjectUtils.isEmpty(handler)) {
            return null;
        }
        return handler.resolve(exception).getMsg();
    }
}
