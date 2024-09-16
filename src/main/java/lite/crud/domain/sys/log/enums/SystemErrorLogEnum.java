package lite.crud.domain.sys.log.enums;

import lite.crud.application.util.extend.UserInfoUtil;
import lite.crud.config.exception.custom.BusinessException;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
public enum SystemErrorLogEnum {

    /* http-client-request */
    RESTFUL {
        @Override
        public String getUserCodeName() {
            return UserInfoUtil.getCurrentLoginUsernameAndCode();
        }
    },
    /* rpc-request */
    RPC {
        @Override
        public String getUserCodeName() {
            throw new BusinessException("no impl");
        }
    },
    /* job-handler */
    JOB {
        @Override
        public String getUserCodeName() {
            throw new BusinessException("no impl");
        }
    };

    public abstract String getUserCodeName();
}
