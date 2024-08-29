package lite.crud.config.exception.handler;

import lite.crud.config.exception.vo.ExceptionHandlerVo;

import java.util.Objects;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
public abstract class AbsExceptionHandler<E extends Exception> implements ExceptionResolveHandler<E> {

    private final Class<E> exceptionClass;

    protected AbsExceptionHandler(final Class<E> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    @Override
    public ExceptionHandlerVo resolveAndRegister(final E exception) {
        final ExceptionHandlerVo resolve = resolve(exception);
        if (resolve != null) {
            ExceptionHandlerConfig.register(exception, this);
        }
        return resolve;
    }

    @Override
    public boolean support(final Exception e) {
        if (Objects.isNull(e) || Objects.isNull(exceptionClass)) {
            return false;
        }

        return e.getClass().getName().equals(exceptionClass.getName());
    }
}
