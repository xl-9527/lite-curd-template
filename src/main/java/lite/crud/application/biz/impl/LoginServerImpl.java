package lite.crud.application.biz.impl;

import lite.crud.application.biz.LoginService;
import lite.crud.domain.biz.sys.dto.LoginDto;
import lite.crud.domain.biz.sys.vo.LoginUserInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author xl-9527
 * @since 2024/7/31
 **/
@Service
public class LoginServerImpl implements LoginService {

    @Override
    public LoginUserInfoVo login(final LoginDto loginDto) {
        return null;
    }
}
