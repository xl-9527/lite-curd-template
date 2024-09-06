package lite.crud.domain.cdata.user.dto;

import lite.crud.application.util.dto.BaseParams;
import lite.crud.domain.cdata.user.bo.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class UserInfoWriteDto extends BaseParams implements Serializable {

	/**
	 * last day login time
	 */
	private LocalDateTime lastLoginTime;

	/**
	 * sign out time
	 */
	private LocalDateTime signOutTime;

	public UserInfo toDbBean() {
		final UserInfo userInfo = new UserInfo();
		userInfo.setLastLoginTime(lastLoginTime);
		userInfo.setSignOutTime(signOutTime);
		final Serializable[] ids = this.getIds();
		if (ObjectUtils.isNotEmpty(ids)) {
			userInfo.setId((Integer) ids[0]);
		}
		return userInfo;
	}
}
