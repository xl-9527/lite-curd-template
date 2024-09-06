package lite.crud.domain.user.bo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class UserInfo {

	private Integer id;

	/**
	 * Login username for this
	 */
	private String username;

	/**
	 * login password
	 */
	private String password;

	/**
	 * last day login time
	 */
	private LocalDateTime lastLoginTime;

	/**
	 * sign out time
	 */
	private LocalDateTime signOutTime;
}
