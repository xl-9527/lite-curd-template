package lite.crud.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lite.crud.application.util.opc.json.JSONOpcUtil;
import lite.crud.config.common.vo.ApiResult;
import lite.crud.domain.sys.dto.LoginDto;
import lite.crud.domain.sys.vo.LoginUserInfoVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xl-9527
 * @since 2024/9/3
 */
@Service
public class SysUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String LOGIN_HASH_KEY = "LOGIN:USER";

    public SysUsernamePasswordAuthenticationFilter(final AuthenticationManager authenticationManager, final RedisTemplate<String, Object> redisTemplate) {
        super(new AntPathRequestMatcher("/sys/login", "POST"), authenticationManager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        final String req = request.getReader().lines().map(v -> new String(v.getBytes(StandardCharsets.UTF_8))).collect(Collectors.joining());
        final LoginDto loginDto = JSONOpcUtil.DEFAULT.parseObject(req, LoginDto.class);

        String username = loginDto.getUsername();
        username = (username != null) ? username.trim() : "";
        String password = loginDto.getPassword();
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        this.setDetails(request, authRequest);
        if (redisTemplate.opsForHash().hasKey(LOGIN_HASH_KEY, username)) {
            // has user info
            final Object userInfo = redisTemplate.opsForHash().get(LOGIN_HASH_KEY, username);
            if (userInfo instanceof LoginUserInfoVo loginUserInfoVo) {
                final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        loginUserInfoVo, null, List.of(new SimpleGrantedAuthority("ADMIN"))
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                return authenticationToken;
            }
        }
        final Authentication authenticate = this.getAuthenticationManager().authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return authenticate;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException {
        // save login userinfo -> redis
        final Object principal = authResult.getPrincipal();
        if (principal instanceof LoginUserInfoVo userDetails) {
            redisTemplate.opsForHash().put(LOGIN_HASH_KEY, userDetails.getUsername(), userDetails);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSONOpcUtil.DEFAULT.toJSONStr(ApiResult.success(authResult)));
    }

    private void setDetails(final HttpServletRequest request, final UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
