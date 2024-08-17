package lite.crud.config.exception.handler.resolve;

import lite.crud.config.exception.handler.AbsExceptionHandler;
import lite.crud.config.exception.vo.ExceptionHandlerVo;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
@Component
public class HttpMessageNotReadableExceptionResolve extends AbsExceptionHandler<HttpMessageNotReadableException> {

    protected HttpMessageNotReadableExceptionResolve() {
        super(HttpMessageNotReadableException.class);
    }

    @Override
    public ExceptionHandlerVo resolve(final HttpMessageNotReadableException exception) {
        final String msg = exception.getMessage();
        if (ObjectUtils.isEmpty(msg)) {
            return ExceptionHandlerVo.builder().msg("请求参数解析失败").build();
        } else if (msg.contains("Required request body is missing")) {
            return ExceptionHandlerVo.builder().msg("请求参数缺失").build();
        }

        return ExceptionHandlerVo.builder().msg("请求参数解析失败").build();
    }
}
