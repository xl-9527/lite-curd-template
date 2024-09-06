package lite.crud.application.handler.user;


import lite.crud.application.handler.user.support.UserInfoCrudServiceSupport;
import lite.crud.domain.user.dto.UserInfoQueryDto;
import lite.crud.domain.user.dto.UserInfoWriteDto;
import lite.crud.domain.user.vo.UserInfoVo;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
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

    private final MysqlInvokeInfrastructure<UserInfoCrudServiceSupport> infrastructure;

    public UserInfoService(final MysqlInvokeInfrastructure<UserInfoCrudServiceSupport> infrastructure) {
        this.infrastructure = infrastructure;
    }

    public List<UserInfoVo> getUserInfo(final UserInfoQueryDto userInfoQueryDto) {
        final List<UserInfoVo> userInfoVos = infrastructure.invoke().doQuery(userInfoQueryDto);
        return Optional.ofNullable(userInfoVos).orElse(Collections.emptyList());
    }

    public void updateById(final UserInfoWriteDto userInfoWriteDto) {
        infrastructure.invoke().doUpdate(userInfoWriteDto);
    }
}
