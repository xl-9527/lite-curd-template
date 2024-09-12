package lite.crud.application.handler.cdata.role;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import lite.crud.infrastructure.persistence.mysql.cdata.role.RoleInfoMysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Service
public class RoleInfoService {

    private final RoleInfoMysqlInvokeInfrastructure roleInfoMysqlInvokeInfrastructure;

    public RoleInfoService(final RoleInfoMysqlInvokeInfrastructure roleInfoMysqlInvokeInfrastructure) {
        this.roleInfoMysqlInvokeInfrastructure = roleInfoMysqlInvokeInfrastructure;
    }

    public Page<RoleInfoVo> queryListPage(final RoleInfoQueryDto roleInfoQueryDto) {
        return roleInfoMysqlInvokeInfrastructure.queryListPage(roleInfoQueryDto.toPage(), roleInfoQueryDto);
    }
}
