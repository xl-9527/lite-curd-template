package lite.crud.config.exception.handler;

import lite.crud.config.exception.vo.ExceptionHandlerVo;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
public interface ExceptionResolveHandler<E extends Exception> {

    /**
     * resolve exception
     *
     * @param exception exception info
     * @return resolve result
     */
    ExceptionHandlerVo resolveAndRegister(final E exception);

    boolean support(final Exception e);

    /**
     * resolve
     */
    ExceptionHandlerVo resolve(E exception);
}
