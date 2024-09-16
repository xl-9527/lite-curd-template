package lite.crud.domain.cdata.user.vo;

import lite.crud.config.security.SysGrantedAuthority;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
public record UserInfoVo(Integer id, String username, String password) implements Serializable {

	public LoginUserInfoVo toLoginUserInfoVo() {
		final LoginUserInfoVo userInfoVo = new LoginUserInfoVo(id, username, password);
		userInfoVo.setLoginTime(LocalDateTime.now());
		userInfoVo.setAuthorities(new ArrayList<>(List.of(new SysGrantedAuthority("ROLE_ADMIN"))));
		return userInfoVo;
	}
}
