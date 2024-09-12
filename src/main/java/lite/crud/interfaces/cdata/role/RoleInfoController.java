package lite.crud.interfaces.cdata.role;

import lite.crud.application.handler.cdata.role.RoleInfoService;
import lite.crud.config.common.pojo.ApiResult;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * role controller with web
 *
 * @author xl-9527
 * @since 2024/9/6
 */
@RestController
@RequestMapping("/cdata/role")
public class RoleInfoController {

    private final RoleInfoService roleService;

    public RoleInfoController(final RoleInfoService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("list")
    public ApiResult<Page<RoleInfoVo>> queryListPage(RoleInfoQueryDto roleInfoQueryDto) {
        return ApiResult.success(roleService.queryListPage(roleInfoQueryDto));
    }
}
