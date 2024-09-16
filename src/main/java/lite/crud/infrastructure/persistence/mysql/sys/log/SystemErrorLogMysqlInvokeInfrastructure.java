package lite.crud.infrastructure.persistence.mysql.sys.log;

import lite.crud.application.handler.sys.log.support.SystemErrorLogCrudServiceSupport;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Service
public class SystemErrorLogMysqlInvokeInfrastructure extends MysqlInvokeInfrastructure<SystemErrorLogCrudServiceSupport> {

    public SystemErrorLogMysqlInvokeInfrastructure(final SystemErrorLogCrudServiceSupport crudService) {
        super(crudService);
    }
}
