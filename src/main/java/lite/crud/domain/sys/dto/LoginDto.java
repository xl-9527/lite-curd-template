package lite.crud.domain.sys.dto;

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
	private String username;

	/**
	 * login password
	 */
	private String password;

	public UserInfoQueryDto toUserInfoQueryDto() {
		final UserInfoQueryDto infoQueryDto = new UserInfoQueryDto(this.username, this.password);

		return infoQueryDto;
	}
}
