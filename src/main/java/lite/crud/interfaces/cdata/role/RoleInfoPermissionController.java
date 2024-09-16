package lite.crud.interfaces.cdata.role;

import lite.crud.application.handler.cdata.role.RoleInfoPermissionService;
import lite.crud.config.common.pojo.ApiResult;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.bo.RoleInfoPermission;
import lite.crud.domain.cdata.role.dto.RoleInfoPermissionQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import org.springframework.web.bind.annotation.*;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@RestController
@RequestMapping("cdata/role-permission")
public class RoleInfoPermissionController {

    private final RoleInfoPermissionService roleInfoPermissionService;

    public RoleInfoPermissionController(final RoleInfoPermissionService roleInfoPermissionService) {
        this.roleInfoPermissionService = roleInfoPermissionService;
    }

    @GetMapping("list")
    public ApiResult<Page<RoleInfoPermissionVo>> queryListPage(RoleInfoPermissionQueryDto roleInfoPermissionQueryDto) {
        return ApiResult.success(roleInfoPermissionService.queryListPage(roleInfoPermissionQueryDto));
    }

    @PostMapping("save")
    public ApiResult<Boolean> save(@RequestBody RoleInfoPermission roleInfoPermission) {
        return ApiResult.success(roleInfoPermissionService.save(roleInfoPermission));
    }
}
