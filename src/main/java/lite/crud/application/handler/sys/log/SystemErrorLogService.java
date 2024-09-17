package lite.crud.application.handler.sys.log;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.sys.log.bo.SystemErrorLog;
import lite.crud.domain.sys.log.dto.SystemErrorLogQueryDto;
import lite.crud.domain.sys.log.enums.SystemErrorLogEnum;
import lite.crud.domain.sys.log.vo.SystemErrorLogVo;
import lite.crud.infrastructure.persistence.mysql.sys.log.SystemErrorLogMysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Service
public class SystemErrorLogService {

    private final SystemErrorLogMysqlInvokeInfrastructure systemErrorLogMysqlInvokeInfrastructure;

    public SystemErrorLogService(final SystemErrorLogMysqlInvokeInfrastructure systemErrorLogMysqlInvokeInfrastructure) {
        this.systemErrorLogMysqlInvokeInfrastructure = systemErrorLogMysqlInvokeInfrastructure;
    }


    public void recordLog(final Exception e, final String finalResult, final SystemErrorLogEnum systemErrorLogEnum) {
        final SystemErrorLog systemErrorLog = SystemErrorLog.initWithDefault(e, finalResult, systemErrorLogEnum);
        systemErrorLogMysqlInvokeInfrastructure.invoke().doSave(systemErrorLog);
    }

    public Page<SystemErrorLogVo> queryListPage(final SystemErrorLogQueryDto queryDto) {
        return systemErrorLogMysqlInvokeInfrastructure.queryListPage(queryDto.toPage(), queryDto);
    }
}
