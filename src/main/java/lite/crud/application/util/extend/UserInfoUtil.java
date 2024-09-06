package lite.crud.application.util.extend;

import lite.crud.domain.sys.vo.LoginUserInfoVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
public class UserInfoUtil {

    public static LoginUserInfoVo getCurrentLoginUserInfoVo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUserInfoVo loginUserInfoVo) {
            return loginUserInfoVo;
        } else if (principal instanceof String) {
            // Anonymous user
            return new LoginUserInfoVo(null, "Anonymous", null);
        }

        return null;
    }
}
