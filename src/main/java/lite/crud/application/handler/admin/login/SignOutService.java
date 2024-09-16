package lite.crud.application.handler.admin.login;

import lite.crud.application.handler.cdata.user.UserInfoService;
import lite.crud.config.common.constant.redis.RedisConstant;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import lite.crud.infrastructure.persistence.redis.HashOps;
import lite.crud.infrastructure.persistence.redis.RedisInvokeInfrastructure;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Service
public class SignOutService {

    private final HashOps<LoginUserInfoVo> hashOps;
    private final UserInfoService userInfoService;

    public SignOutService(RedisInvokeInfrastructure<LoginUserInfoVo> redisInvokeInfrastructure, final UserInfoService userInfoService) {
        this.hashOps = redisInvokeInfrastructure.opsHash(RedisConstant.USER_LOGIN_HASH_KEY);
        this.userInfoService = userInfoService;
    }

    public boolean signOut(final String username) {
        if (ObjectUtils.isEmpty(username) || !hashOps.containsKey(username)) {
            return false;
        }
        final LoginUserInfoVo loginUserInfoVo = hashOps.remove(username);
        // update last login time and sign out time
        if (ObjectUtils.isNotEmpty(loginUserInfoVo)) {
            userInfoService.updateById(loginUserInfoVo.toUserInfoWriteDto(LocalDateTime.now()));
            return true;
        }
        return false;
    }
}
