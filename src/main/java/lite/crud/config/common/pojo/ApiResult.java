package lite.crud.config.common.pojo;

import lite.crud.config.common.constant.ResponseRestCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
@Getter
@Setter
public class ApiResult<T> {

    public ApiResult(final boolean success, final int code, final String msg, final T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    /**
     * response code {@link lite.crud.config.common.constant.ResponseRestCode}
     */
    private int code;

    /**
     * msg with front
     */
    private String msg;

    /**
     * Is ok ?
     */
    private boolean success;

    /**
     * 携带的数据集合
     */
    private T data;

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(true, ResponseRestCode.OK, null, null);
    }

    public static <T> ApiResult<T> success(final T data) {
        return new ApiResult<>(true, ResponseRestCode.OK, null, data);
    }

    public static ApiResult<String> fail(String msg) {
        return new ApiResult<>(false, ResponseRestCode.SERVER_ERROR, msg, null);
    }
}
