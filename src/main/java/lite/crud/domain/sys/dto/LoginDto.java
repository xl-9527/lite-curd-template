package lite.crud.domain.sys.dto;

import jakarta.validation.constraints.NotEmpty;
import lite.crud.domain.user.dto.UserInfoQueryDto;
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
