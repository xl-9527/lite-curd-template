package lite.crud.application.handler.cdata.user;


import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lite.crud.domain.cdata.user.dto.UserInfoWriteDto;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import lite.crud.infrastructure.persistence.mysql.cdata.user.UserInfoMysqlInvokeInfrastructure;
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

    private final UserInfoMysqlInvokeInfrastructure infrastructure;

    public UserInfoService(final UserInfoMysqlInvokeInfrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public List<UserInfoVo> getUserInfo(final UserInfoQueryDto userInfoQueryDto) {
        final List<UserInfoVo> userInfoVos = infrastructure.invoke().doQuery(userInfoQueryDto);
        return Optional.ofNullable(userInfoVos).orElse(Collections.emptyList());
    }

    public void updateById(final UserInfoWriteDto userInfoWriteDto) {
        infrastructure.invoke().doUpdate(userInfoWriteDto);
    }

    public Page<UserInfoVo> queryListPage(final UserInfoQueryDto userInfoQueryDto) {
        return infrastructure.queryListPage(userInfoQueryDto.toPage(), userInfoQueryDto);
    }
}
