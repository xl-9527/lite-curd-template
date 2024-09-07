package lite.crud.infrastructure.persistence.mysql.cdata.user;

import lite.crud.application.handler.cdata.user.support.UserInfoCrudServiceSupport;
import lite.crud.infrastructure.persistence.mysql.MysqlInvokeInfrastructure;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/9/7
 **/
@Service
public class UserInfoMysqlInvokeInfrastructure extends MysqlInvokeInfrastructure<UserInfoCrudServiceSupport> {

    public UserInfoMysqlInvokeInfrastructure(final UserInfoCrudServiceSupport userInfoCrudServiceSupport) {
        super(userInfoCrudServiceSupport);
    }
}
