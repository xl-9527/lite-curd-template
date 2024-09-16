package lite.crud.domain.cdata.role.bo;

import lite.crud.config.common.pojo.BaseDbField;
import lombok.Getter;
import lombok.Setter;

/**
 * RoleInfoPermission update and update this table filed updateTime
 *
 * @author xl-9527
 * @since 2024/9/6
 */
@Getter
@Setter
public class RoleInfo extends BaseDbField {

    private Integer id;

    /**
     * role name
     */
    private String roleName;

    /**
     * role code
     */
    private String roleCode;

    /**
     * description info with current role
     */
    private String description;
}
