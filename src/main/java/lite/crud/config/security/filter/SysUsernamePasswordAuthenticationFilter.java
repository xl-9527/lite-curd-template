package lite.crud.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lite.crud.application.util.opc.json.JSONOpcUtil;
import lite.crud.config.common.constant.redis.RedisConstant;
import lite.crud.config.common.pojo.ApiResult;
import lite.crud.config.security.SysGrantedAuthority;
import lite.crud.domain.sys.login.dto.LoginDto;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import lite.crud.infrastructure.persistence.redis.HashOps;
import lite.crud.infrastructure.persistence.redis.RedisInvokeInfrastructure;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户登录拦截
 *
 * @author xl-9527
 * @since 2024/9/3
 */
@Service
public class SysUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final RedisInvokeInfrastructure<LoginUserInfoVo> redisInvokeInfrastructure;

    public SysUsernamePasswordAuthenticationFilter(final AuthenticationManager authenticationManager, final RedisInvokeInfrastructure<LoginUserInfoVo> redisInvokeInfrastructure) {
        super(new AntPathRequestMatcher("/sys/login", "POST"), authenticationManager);
        this.redisInvokeInfrastructure = redisInvokeInfrastructure;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        final String req = request.getReader().lines().map(v -> new String(v.getBytes(StandardCharsets.UTF_8))).collect(Collectors.joining());
        if (ObjectUtils.isEmpty(req)) {
            throw new AuthenticationServiceException("Request body is empty");
        }
        final LoginDto loginDto = JSONOpcUtil.DEFAULT.parseObject(req, LoginDto.class);

        String username = loginDto.getUsername();
        username = (username != null) ? username.trim() : "";
        String password = loginDto.getPassword();
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        this.setDetails(request, authRequest);
        final HashOps<LoginUserInfoVo> userInfoVoMap = redisInvokeInfrastructure.opsHash(RedisConstant.USER_LOGIN_HASH_KEY);
        if (userInfoVoMap.containsKey(username)) {
            // has user info
            final LoginUserInfoVo loginUserInfoVo = userInfoVoMap.get(username);
            loginUserInfoVo.setLoginTime(LocalDateTime.now());
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginUserInfoVo, null, List.of(new SysGrantedAuthority("ROLE_ADMIN"))
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return authenticationToken;
        }

        // 首次登录
        final Authentication authenticate = this.getAuthenticationManager().authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return authenticate;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException {
        // save login userinfo -> redis
        final Object principal = authResult.getPrincipal();
        if (principal instanceof LoginUserInfoVo userDetails) {
            redisInvokeInfrastructure.opsHash(RedisConstant.USER_LOGIN_HASH_KEY).put(userDetails.getUsername(), userDetails);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSONOpcUtil.DEFAULT.toJSONStr(ApiResult.success(authResult)));
    }

    private void setDetails(final HttpServletRequest request, final UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
