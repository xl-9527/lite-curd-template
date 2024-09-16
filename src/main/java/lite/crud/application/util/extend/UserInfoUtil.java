package lite.crud.application.util.extend;

import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.security.auth.login.FailedLoginException;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
public class UserInfoUtil {

    public static LoginUserInfoVo getCurrentLoginUserInfoVo() throws FailedLoginException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUserInfoVo loginUserInfoVo) {
            return loginUserInfoVo;
        } else if (principal instanceof String) {
            // Anonymous user
            return new LoginUserInfoVo(null, "Anonymous", null);
        }

        throw new FailedLoginException("Unknown user");
    }

    public static String getCurrentLoginUsernameAndCode() {
        try {
            final LoginUserInfoVo currentLoginUserInfoVo = getCurrentLoginUserInfoVo();
            return currentLoginUserInfoVo.getId() + "-" + currentLoginUserInfoVo.getUsername();
        } catch (FailedLoginException e) {
            throw new RuntimeException(e);
        }
    }
}
