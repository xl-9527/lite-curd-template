package lite.crud.domain.user.dto;

import lite.crud.application.util.dto.BaseParams;
import lite.crud.domain.user.bo.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class UserInfoWriteDto extends BaseParams implements Serializable {

	public UserInfo toDbBean() {
		final UserInfo userInfo = new UserInfo();
		return userInfo;
	}
}
