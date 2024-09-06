package lite.crud.interfaces.amdin.login;

import jakarta.servlet.http.HttpServletRequest;
import lite.crud.application.handler.admin.login.SignOutService;
import lite.crud.config.common.constant.sys.user.CustomRpcHeaderWithLogin;
import lite.crud.config.common.pojo.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@RestController
@RequestMapping("/admin/login/sign-out")
public class SignOutController {

    private final SignOutService signOutService;

    public SignOutController(final SignOutService signOutService) {
        this.signOutService = signOutService;
    }

    /**
     * 退出登录
     */
    @PostMapping
    public ApiResult<Boolean> signOut(final HttpServletRequest request) {
        return ApiResult.success(signOutService.signOut(request.getHeader(CustomRpcHeaderWithLogin.USER_ID)));
    }
}
