package lite.crud.application.handler.cdata.role;

import lite.crud.application.handler.cdata.role.support.RoleInfoCrudServiceSupport;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Service
public class RoleInfoService {

    private final RoleInfoCrudServiceSupport roleInfoCrudServiceSupport;

    public RoleInfoService(final RoleInfoCrudServiceSupport roleInfoCrudServiceSupport) {
        this.roleInfoCrudServiceSupport = roleInfoCrudServiceSupport;
    }

    public Page<RoleInfoVo> list(final RoleInfoQueryDto roleInfoQueryDto) {
        return null;
    }
}
