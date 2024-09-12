package lite.crud.application.handler.cdata.user.support;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lite.crud.domain.cdata.user.dto.UserInfoWriteDto;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import lite.crud.infrastructure.persistence.mysql.cdata.user.UserInfoMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Service
public class UserInfoCrudServiceSupport implements BizEventCrudService<UserInfoVo, UserInfoWriteDto, UserInfoQueryDto> {

    private final UserInfoMapper userInfoMapper;

    private final Logger log = LoggerFactory.getLogger(UserInfoCrudServiceSupport.class);

    public UserInfoCrudServiceSupport(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public Boolean doSave(UserInfoWriteDto loginUserInfoQueryDto) {
        return userInfoMapper.doSave(loginUserInfoQueryDto.toDbBean());
    }

    @Override
    public void doUpdate(UserInfoWriteDto userInfoWriteDto) {
        final Serializable[] ids = userInfoWriteDto.getIds();
        if (ObjectUtils.isEmpty(ids)) {
            log.warn("update with id, bug not fount ids -> {}", Arrays.toString(ids));
            return;
        }

        userInfoMapper.doUpdate(userInfoWriteDto);
    }

    @Override
    public void doUpdate(final Map<String, Object> updateMap) {

    }

    @Override
    public Boolean doDelete(List<Integer> ids) {
        return userInfoMapper.deleteByIds(ids) != 0;
    }

    @Override
    public List<UserInfoVo> doGetByIds(final Serializable[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return null;
        }

        return this.doQuery(new UserInfoQueryDto().builder().ids(ids).build());
    }

    @Override
    public UserInfoVo doGetById(final Serializable id) {
        final List<UserInfoVo> userInfoVos = doGetByIds(new Serializable[]{id});

        if (ObjectUtils.isNotEmpty(userInfoVos)) {
            return userInfoVos.get(0);
        }
        return null;
    }

    @Override
    public List<UserInfoVo> doQueryListPage(final Page<UserInfoVo> page, final UserInfoQueryDto userInfoQueryDto) {
        return userInfoMapper.doQuery(page, userInfoQueryDto);
    }

    @Override
    public List<UserInfoVo> doQuery(final UserInfoQueryDto userInfoQueryDto) {
        return userInfoMapper.doQuery(null, userInfoQueryDto);
    }
}
