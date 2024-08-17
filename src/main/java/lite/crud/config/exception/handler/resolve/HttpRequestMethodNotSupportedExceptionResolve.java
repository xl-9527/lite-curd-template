package lite.crud.config.exception.handler.resolve;

import lite.crud.config.exception.handler.AbsExceptionHandler;
import lite.crud.config.exception.vo.ExceptionHandlerVo;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
@Component
public class HttpRequestMethodNotSupportedExceptionResolve extends AbsExceptionHandler<HttpRequestMethodNotSupportedException> {

    public HttpRequestMethodNotSupportedExceptionResolve() {
        super(HttpRequestMethodNotSupportedException.class);
    }

    @Override
    public ExceptionHandlerVo resolve(final HttpRequestMethodNotSupportedException exception) {
        return ExceptionHandlerVo.builder().msg("不支持的请求方式").build();
    }
}
