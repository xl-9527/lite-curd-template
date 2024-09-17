package lite.crud.domain.sys.log.bo;

import lite.crud.application.util.opc.json.JSONOpcUtil;
import lite.crud.config.common.pojo.BaseDbField;
import lite.crud.domain.sys.log.enums.SystemErrorLogEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

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
    private Integer errorCode;

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
        final String usernameAndCode = systemErrorLogEnum.getUserCodeName();

        systemErrorLog.setId(null);
        systemErrorLog.setErrorCode(-1);
        systemErrorLog.setErrorType(systemErrorLogEnum.name());
        if (ObjectUtils.isNotEmpty(finalResult)) {
            systemErrorLog.setErrorMsg(finalResult.length() > 255 ? finalResult.substring(0, 255) : finalResult);
        } else {
            systemErrorLog.setErrorMsg(StringUtils.EMPTY);
        }
        systemErrorLog.setErrorDetail(JSONOpcUtil.DEFAULT.toJSONStr(e.getStackTrace()));
        systemErrorLog.setCreateTime(now);
        systemErrorLog.setUpdateTime(now);
        systemErrorLog.setCreateBy(usernameAndCode);
        systemErrorLog.setUpdateBy(usernameAndCode);
        return systemErrorLog;
    }
}
