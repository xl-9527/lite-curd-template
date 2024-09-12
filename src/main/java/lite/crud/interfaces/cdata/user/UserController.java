package lite.crud.interfaces.cdata.user;

import lite.crud.application.handler.cdata.user.UserInfoService;
import lite.crud.config.common.pojo.ApiResult;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xl-9527
 * @since 2024/7/26
 **/
@RestController
@RequestMapping("/cdata/user")
public class UserController {

    private final UserInfoService userInfoService;

    public UserController(final UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("list")
    public ApiResult<Page<UserInfoVo>> queryListPage(UserInfoQueryDto userInfoQueryDto) {
        return ApiResult.success(userInfoService.queryListPage(userInfoQueryDto));
    }
}
