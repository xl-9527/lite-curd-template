package lite.crud.infrastructure.persistence.mysql.cdata.user;

import lite.crud.application.handler.cdata.user.support.UserInfoCrudServiceSupport;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/7
 **/
@Service
public class UserInfoMysqlInvokeInfrastructure extends MysqlInvokeInfrastructure<UserInfoQueryDto, UserInfoVo, UserInfoCrudServiceSupport> {

    public UserInfoMysqlInvokeInfrastructure(final UserInfoCrudServiceSupport userInfoCrudServiceSupport) {
        super(userInfoCrudServiceSupport);
    }

    public Page<UserInfoVo> queryListPage(final Page<UserInfoVo> page, final UserInfoQueryDto userInfoQueryDto) {
        crudService.doQueryListPage(page, userInfoQueryDto);
        return page;
    }
}
