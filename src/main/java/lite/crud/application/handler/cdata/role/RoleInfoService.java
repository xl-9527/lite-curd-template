package lite.crud.application.handler.cdata.role;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import lite.crud.infrastructure.persistence.mysql.cdata.role.RoleInfoMysqlInvokeInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Service
public class RoleInfoService {

    private final RoleInfoMysqlInvokeInvokeInfrastructure roleInfoMysqlInvokeInvokeInfrastructure;

    public RoleInfoService(final RoleInfoMysqlInvokeInvokeInfrastructure roleInfoMysqlInvokeInvokeInfrastructure) {
        this.roleInfoMysqlInvokeInvokeInfrastructure = roleInfoMysqlInvokeInvokeInfrastructure;
    }

    public Page<RoleInfoVo> listPage(final RoleInfoQueryDto roleInfoQueryDto) {
        return roleInfoMysqlInvokeInvokeInfrastructure.listPage(roleInfoQueryDto.toPage(), roleInfoQueryDto);
    }
}
