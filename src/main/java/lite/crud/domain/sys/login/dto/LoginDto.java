package lite.crud.domain.sys.login.dto;

import jakarta.validation.constraints.NotEmpty;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
@Getter
@Setter
public class LoginDto implements Serializable {

	public LoginDto() {
	}

	public LoginDto(final String password, final String username, final int loginType) {
		this.password = password;
		this.username = username;
		this.loginType = loginType;
	}

	/**
	 * login type, default value is zero
	 *
	 * @see lite.crud.config.common.constant.sys.user.LoginType
	 */
	private int loginType;

	/**
	 * Login username for this
	 */
	@NotEmpty(message = "username 不可以为空")
	private String username;

	/**
	 * login password
	 */
	@NotEmpty(message = "password 不可以为空")
	private String password;

	public UserInfoQueryDto toUserInfoQueryDto() {
		return new UserInfoQueryDto(this.username, this.password);
	}
}
