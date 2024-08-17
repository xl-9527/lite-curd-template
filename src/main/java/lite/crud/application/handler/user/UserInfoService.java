package lite.crud.application.handler.user;


import lite.crud.application.handler.user.support.UserInfoCrudServiceSupport;
import lite.crud.domain.user.dto.UserInfoQueryDto;
import lite.crud.domain.user.vo.UserInfoVo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Service
public class UserInfoService {

	private final UserInfoCrudServiceSupport userInfoCrudServiceSupport;

	public UserInfoService(UserInfoCrudServiceSupport userInfoCrudServiceSupport) {
		this.userInfoCrudServiceSupport = userInfoCrudServiceSupport;
	}

	public List<UserInfoVo> getUserInfo(final UserInfoQueryDto userInfoQueryDto) {
		return Optional.ofNullable(userInfoCrudServiceSupport.doQuery(userInfoQueryDto)).orElse(Collections.emptyList());
	}
}
