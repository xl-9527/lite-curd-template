package lite.crud.application.handler.sys;

import lite.crud.application.handler.user.UserInfoService;
import lite.crud.domain.sys.dto.LoginDto;
import lite.crud.domain.sys.vo.LoginUserInfoVo;
import lite.crud.domain.user.vo.UserInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

	public LoginUserInfoVo login(LoginDto loginDto) {
		List<UserInfoVo> userInfoVoList = userInfoService.getUserInfo(loginDto.toUserInfoQueryDto());
		if (ObjectUtils.isEmpty(userInfoVoList)) {
			throw new RuntimeException("用户不存在");
		}

		return userInfoVoList.get(0).toLoginUserInfoVo();
	}
}
