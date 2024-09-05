package lite.crud.config.exception.custom;

/**
 * business exception with global, can use this exception elegant tips user
 *
 * @author xl-9527
 * @since 2024/9/5
 **/
public class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
