package lite.crud.domain.user.bo;

import lombok.Getter;
import lombok.Setter;

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
}
