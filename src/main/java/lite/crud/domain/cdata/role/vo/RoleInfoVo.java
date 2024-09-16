package lite.crud.domain.cdata.role.vo;

import lite.crud.domain.cdata.role.bo.RoleInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/7
 **/
@Getter
@Setter
public class RoleInfoVo extends RoleInfo {

    private List<RoleInfoPermissionVo> roleInfoPermissionVos;
}
