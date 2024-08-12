package lite.crud.config.exception.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
@Setter
@Getter
public class ExceptionHandlerVo implements Serializable {

    private String msg;

    public static ExceptionHandlerVoBuilder builder() {
        return new ExceptionHandlerVoBuilder();
    }

    /**
     * builder
     */
    public static class ExceptionHandlerVoBuilder {
        private String msg;

        public ExceptionHandlerVoBuilder msg(final String msg) {
            this.msg = msg;
            return this;
        }

        public ExceptionHandlerVo build() {
            final ExceptionHandlerVo exceptionHandlerVo = new ExceptionHandlerVo();
            exceptionHandlerVo.msg = this.msg;
            return exceptionHandlerVo;
        }
    }
}
