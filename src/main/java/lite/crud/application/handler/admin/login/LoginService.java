package lite.crud.application.handler.admin.login;

import lite.crud.application.handler.cdata.user.UserInfoService;
import lite.crud.config.common.constant.sys.user.LoginType;
import lite.crud.config.exception.custom.BusinessException;
import lite.crud.domain.sys.login.dto.LoginDto;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Service
public class LoginService {

    private final UserInfoService userInfoService;

    public LoginService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    private LoginUserInfoVo doLoginWithUsernamePassword(LoginDto loginDto) {
        if (ObjectUtils.anyNull(loginDto.getUsername(), loginDto.getLoginType())) {
            throw new IllegalArgumentException("login user loginType or username is null");
        }
        List<UserInfoVo> userInfoVoList = userInfoService.getUserInfo(loginDto.toUserInfoQueryDto());
        if (ObjectUtils.isEmpty(userInfoVoList)) {
            throw new RuntimeException("user not found with username -> " + loginDto.getUsername());
        }

        return userInfoVoList.get(0).toLoginUserInfoVo();
    }

    /**
     * login with login type
     *
     * @param loginDto  login params
     * @param loginType {@link lite.crud.config.common.constant.sys.user.LoginType}
     * @return login user info
     */
    public LoginUserInfoVo loginWithType(LoginDto loginDto, final int loginType) {
        if (loginType == LoginType.DEFAULT_LOGIN_TYPE) {
            return this.doLoginWithUsernamePassword(loginDto);
        }

        throw new BusinessException("login type error -> " + loginType);
    }
}
