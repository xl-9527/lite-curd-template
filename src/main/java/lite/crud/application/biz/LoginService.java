package lite.crud.application.biz;

import lite.crud.domain.biz.sys.dto.LoginDto;
import lite.crud.domain.biz.sys.vo.LoginUserInfoVo;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
public interface LoginService {

    LoginUserInfoVo login(final LoginDto loginDto);
}
