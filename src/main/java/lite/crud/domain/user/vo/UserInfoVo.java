package lite.crud.domain.user.vo;

import lite.crud.domain.sys.vo.LoginUserInfoVo;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
public record UserInfoVo(Integer id, String username, String password) implements Serializable {

	public LoginUserInfoVo toLoginUserInfoVo() {
		return new LoginUserInfoVo(id, username, password);
	}
}
