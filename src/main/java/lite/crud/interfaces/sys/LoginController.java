package lite.crud.interfaces.sys;

import lite.crud.application.biz.LoginService;
import lite.crud.config.common.vo.ApiResult;
import lite.crud.domain.biz.sys.dto.LoginDto;
import lite.crud.domain.biz.sys.vo.LoginUserInfoVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user login
 *
 * @author xl-9527
 * @since 2024/7/26
 **/
@RestController
@RequestMapping("/sys/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("login")
    public ApiResult<LoginUserInfoVo> login(@RequestBody final LoginDto loginDto) {
        return ApiResult.success(loginService.login(loginDto));
    }
}
