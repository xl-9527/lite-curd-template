package lite.crud.application.handler.user.support;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.domain.user.dto.UserInfoQueryDto;
import lite.crud.domain.user.dto.UserInfoWriteDto;
import lite.crud.domain.user.vo.UserInfoVo;
import lite.crud.infrastructure.persistence.mysql.user.UserInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Service
public class UserInfoCrudServiceSupport implements BizEventCrudService<UserInfoVo, UserInfoWriteDto, UserInfoQueryDto> {

	private final UserInfoMapper userInfoMapper;

	public UserInfoCrudServiceSupport(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	@Override
	public void doCreate(UserInfoWriteDto loginUserInfoQueryDto) {
		userInfoMapper.insert(loginUserInfoQueryDto.toDbBean());
	}

	@Override
	public void doUpdate(UserInfoWriteDto updateMap) {

	}

	@Override
	public void doUpdate(final Map<String, Object> updateMap) {

	}

	@Override
	public Boolean doDelete(List<Integer> ids) {
		return userInfoMapper.deleteByIds(ids) != 0;
	}

	@Override
	public List<UserInfoVo> doQuery(final UserInfoQueryDto userInfoQueryDto) {
		return userInfoMapper.doQuery(userInfoQueryDto);
	}
}
