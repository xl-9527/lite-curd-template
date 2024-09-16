package lite.crud.domain.cdata.role.dto;

import lite.crud.application.util.dto.PageParams;
import lite.crud.domain.cdata.role.bo.RoleInfoPermission;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@Getter
@Setter
public class RoleInfoPermissionQueryDto extends PageParams {

    public RoleInfoPermissionQueryDto() {
    }

    public RoleInfoPermissionQueryDto(RoleInfoVo roleInfoVo) {
        this.roleInfoId = roleInfoVo.getId();
        super.setPageSize(1_000);
    }

    /**
     * role info identity
     */
    private Integer roleInfoId;
}
