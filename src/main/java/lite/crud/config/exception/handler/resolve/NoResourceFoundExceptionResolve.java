package lite.crud.config.exception.handler.resolve;

import lite.crud.config.exception.handler.AbsExceptionHandler;
import lite.crud.config.exception.vo.ExceptionHandlerVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
@Component
public class NoResourceFoundExceptionResolve extends AbsExceptionHandler<NoResourceFoundException> {

    protected NoResourceFoundExceptionResolve() {
        super(NoResourceFoundException.class);
    }

    @Override
    public ExceptionHandlerVo resolve(final NoResourceFoundException exception) {
        return ExceptionHandlerVo.builder().msg("路径 -> 【" + exception.getResourcePath() + "】无法匹配").build();
    }
}
