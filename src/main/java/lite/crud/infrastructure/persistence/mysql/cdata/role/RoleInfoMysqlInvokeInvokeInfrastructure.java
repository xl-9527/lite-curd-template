package lite.crud.infrastructure.persistence.mysql.cdata.role;

import lite.crud.application.handler.cdata.role.support.RoleInfoCrudServiceSupport;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@Service
public class RoleInfoMysqlInvokeInvokeInfrastructure extends MysqlInvokeInfrastructure<RoleInfoCrudServiceSupport> {

    public RoleInfoMysqlInvokeInvokeInfrastructure(final RoleInfoCrudServiceSupport crudService) {
        super(crudService);
    }

    public Page<RoleInfoVo> listPage(final Page<RoleInfoVo> page, final RoleInfoQueryDto roleInfoQueryDto) {
        crudService.doQueryListPage(page, roleInfoQueryDto);
        return page;
    }
}
