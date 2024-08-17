package lite.crud.domain.user.vo;

import lite.crud.domain.sys.vo.LoginUserInfoVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class UserInfoVo implements Serializable {

	public static final UserInfoVo EMPTY = new UserInfoVo();

	private Integer id;

	private String username;

	private String password;

	public LoginUserInfoVo toLoginUserInfoVo() {
		return new LoginUserInfoVo(id, username, password);
	}
}
