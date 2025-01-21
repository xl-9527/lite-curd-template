package lite.crud.infrastructure.persistence.mysql.cdata.role;

import lite.crud.application.handler.cdata.role.support.RoleInfoPermissionCrudServiceSupport;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoPermissionQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@Service
public class RoleInfoPermissionMysqlInvokeInfrastructure extends MysqlInvokeInfrastructure<RoleInfoPermissionQueryDto, RoleInfoPermissionVo, RoleInfoPermissionCrudServiceSupport> {

    public RoleInfoPermissionMysqlInvokeInfrastructure(final RoleInfoPermissionCrudServiceSupport crudService) {
        super(crudService);
    }

    public Page<RoleInfoPermissionVo> queryListPage(final Page<RoleInfoPermissionVo> page, final RoleInfoPermissionQueryDto roleInfoPermissionQueryDto) {
        crudService.doQueryListPage(page, roleInfoPermissionQueryDto);
        return page;
    }
}
