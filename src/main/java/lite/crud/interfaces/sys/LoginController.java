package lite.crud.interfaces.sys;

import lite.crud.application.handler.sys.LoginService;
import lite.crud.config.common.vo.ApiResult;
import lite.crud.domain.sys.dto.LoginDto;
import lite.crud.domain.sys.vo.LoginUserInfoVo;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping
    public ApiResult<LoginUserInfoVo> login(@RequestBody @Validated final LoginDto loginDto) {
        return ApiResult.success(loginService.login(loginDto));
    }
}
