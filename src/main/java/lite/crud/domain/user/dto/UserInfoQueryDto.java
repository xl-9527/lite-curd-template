package lite.crud.domain.user.dto;

import lite.crud.application.util.dto.PageParams;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class UserInfoQueryDto extends PageParams implements Serializable {

	public UserInfoQueryDto() {
	}

	public UserInfoQueryDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Login username for this
	 */
	private String username;

	/**
	 * login password
	 */
	private String password;
}
