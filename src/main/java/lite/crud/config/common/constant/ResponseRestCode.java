package lite.crud.config.common.constant;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
public interface ResponseRestCode {

    /**
     * SUCCESS
     */
    int OK = 200;

    /**
     * BAD_REQUEST
     */
    int BAD_REQUEST = 400;

    int UNAUTHORIZED = 401;

    int FORBIDDEN = 403;

    int NOT_FOUND = 404;

    int METHOD_NOT_ALLOWED = 405;

    int NOT_ACCEPTABLE = 406;

    int SERVER_ERROR = 500;
}
