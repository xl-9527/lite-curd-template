package lite.crud.config.common.vo;

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

    public ApiResult(final int code, final String msg, final T data) {
        this.code = code;
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
     * 携带的数据集合
     */
    private T data;

    public static ApiResult<String> success() {
        return new ApiResult<>(ResponseRestCode.OK, null, null);
    }

    public static <T> ApiResult<T> success(final T data) {
        return new ApiResult<>(ResponseRestCode.OK, null, data);
    }

    public static ApiResult<String> fail(String msg) {
        return new ApiResult<>(ResponseRestCode.SERVER_ERROR, msg, null);
    }
}
