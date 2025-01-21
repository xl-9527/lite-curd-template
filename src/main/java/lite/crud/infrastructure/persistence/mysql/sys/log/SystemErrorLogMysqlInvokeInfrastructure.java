package lite.crud.infrastructure.persistence.mysql.sys.log;

import lite.crud.application.handler.sys.log.support.SystemErrorLogCrudServiceSupport;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.sys.log.dto.SystemErrorLogQueryDto;
import lite.crud.domain.sys.log.vo.SystemErrorLogVo;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Service
public class SystemErrorLogMysqlInvokeInfrastructure extends MysqlInvokeInfrastructure<SystemErrorLogQueryDto, SystemErrorLogVo, SystemErrorLogCrudServiceSupport> {

    public SystemErrorLogMysqlInvokeInfrastructure(final SystemErrorLogCrudServiceSupport crudService) {
        super(crudService);
    }

    @Override
    public Page<SystemErrorLogVo> queryListPage(final Page<SystemErrorLogVo> page, final SystemErrorLogQueryDto systemErrorLogQueryDto) {
        invoke().doQueryListPage(page, systemErrorLogQueryDto);
        return page;
    }
}
