package lite.crud.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lite.crud.config.common.constant.redis.RedisConstant;
import lite.crud.config.common.constant.sys.user.CustomRpcHeaderWithLogin;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import lite.crud.infrastructure.persistence.redis.HashOps;
import lite.crud.infrastructure.persistence.redis.RedisInvokeInfrastructure;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author xl-9527
 * @since 2024/9/5
 */
public class SysTokenFilter extends OncePerRequestFilter {

    private final RedisInvokeInfrastructure<LoginUserInfoVo> redisInvokeInfrastructure;

    public SysTokenFilter(final RedisInvokeInfrastructure<LoginUserInfoVo> redisInvokeInfrastructure) {
        this.redisInvokeInfrastructure = redisInvokeInfrastructure;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, @NonNull final HttpServletResponse response, @NonNull final FilterChain filterChain) throws ServletException, IOException {
        final String userId = request.getHeader(CustomRpcHeaderWithLogin.USER_ID);
        if (ObjectUtils.isNotEmpty(userId)) {
            final HashOps<LoginUserInfoVo> loginUserInfoVoHashOps = redisInvokeInfrastructure.opsHash(RedisConstant.USER_LOGIN_HASH_KEY);
            final LoginUserInfoVo loginUserInfoVo = loginUserInfoVoHashOps.get(userId);
            if (ObjectUtils.isNotEmpty(loginUserInfoVo)) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(loginUserInfoVo, null, loginUserInfoVo.getAuthorities())
                );
            }
        }
        filterChain.doFilter(request, response);
    }
}
