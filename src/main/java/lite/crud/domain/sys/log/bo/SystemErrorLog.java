package lite.crud.domain.sys.log.bo;

import lite.crud.application.util.extend.UserInfoUtil;
import lite.crud.application.util.opc.json.JSONOpcUtil;
import lite.crud.config.common.pojo.BaseDbField;
import lite.crud.domain.sys.log.enums.SystemErrorLogEnum;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Getter
@Setter
public class SystemErrorLog extends BaseDbField {

    private Integer id;

    /**
     * error code {@link lite.crud.domain.sys.log.enums.SystemErrorLogEnum}
     */
    private String errorType;

    /**
     * system error code with custom
     */
    private String errorCode;

    /**
     * exception's msg
     */
    private String errorMsg;

    /**
     * error detail of stack
     */
    private String errorDetail;

    public static SystemErrorLog initWithDefault(final Exception e, final String finalResult, final SystemErrorLogEnum systemErrorLogEnum) {
        final SystemErrorLog systemErrorLog = new SystemErrorLog();

        final LocalDateTime now = LocalDateTime.now();
        final String usernameAndCode = UserInfoUtil.getCurrentLoginUsernameAndCode();

        systemErrorLog.setId(null);
        systemErrorLog.setErrorType(systemErrorLogEnum.name());
        systemErrorLog.setErrorCode(null);
        systemErrorLog.setErrorMsg(finalResult);
        systemErrorLog.setErrorDetail(JSONOpcUtil.DEFAULT.toJSONStr(e.getStackTrace()));
        systemErrorLog.setCreateTime(now);
        systemErrorLog.setUpdateTime(now);
        systemErrorLog.setCreateBy(usernameAndCode);
        systemErrorLog.setUpdateBy(usernameAndCode);
        return systemErrorLog;
    }
}
