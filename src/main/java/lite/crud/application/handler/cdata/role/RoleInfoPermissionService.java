package lite.crud.application.handler.cdata.role;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.bo.RoleInfoPermission;
import lite.crud.domain.cdata.role.dto.RoleInfoPermissionQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import lite.crud.infrastructure.persistence.mysql.cdata.role.RoleInfoPermissionMysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@Service
public class RoleInfoPermissionService {

    private final RoleInfoPermissionMysqlInvokeInfrastructure roleInfoPermissionMysqlInvokeInfrastructure;

    public RoleInfoPermissionService(final RoleInfoPermissionMysqlInvokeInfrastructure roleInfoPermissionMysqlInvokeInfrastructure) {
        this.roleInfoPermissionMysqlInvokeInfrastructure = roleInfoPermissionMysqlInvokeInfrastructure;
    }

    public Page<RoleInfoPermissionVo> queryListPage(final RoleInfoPermissionQueryDto roleInfoPermissionQueryDto) {
        return roleInfoPermissionMysqlInvokeInfrastructure.queryListPage(roleInfoPermissionQueryDto.toPage(), roleInfoPermissionQueryDto);
    }

    public Boolean save(final RoleInfoPermission roleInfoPermission) {
        return roleInfoPermissionMysqlInvokeInfrastructure.invoke().doSave(roleInfoPermission);
    }
}
