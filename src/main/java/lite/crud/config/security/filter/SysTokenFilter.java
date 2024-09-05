package lite.crud.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lite.crud.config.common.constant.redis.RedisConstant;
import lite.crud.domain.sys.vo.LoginUserInfoVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
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

    private final RedisTemplate<String, Object> redisTemplate;

    public SysTokenFilter(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, @NonNull final HttpServletResponse response, @NonNull final FilterChain filterChain) throws ServletException, IOException {
        final String userId = request.getHeader("user_id");
        if (ObjectUtils.isNotEmpty(userId)) {
            final Object obj = redisTemplate.opsForHash().get(RedisConstant.USER_LOGIN_HASH_KEY, userId);
            if (obj instanceof LoginUserInfoVo loginUserInfoVo) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(loginUserInfoVo, null, loginUserInfoVo.getAuthorities())
                );
            } else {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(obj, null)
                );
            }
        }
        filterChain.doFilter(request, response);
    }
}
