package lite.crud.config.security;

import lite.crud.application.handler.cdata.role.RoleInfoPermissionService;
import lite.crud.application.handler.cdata.role.RoleInfoService;
import lite.crud.config.security.filter.SysAccessDeniedHandler;
import lite.crud.config.security.filter.SysBasicAuthenticationEntryPoint;
import lite.crud.config.security.filter.SysTokenFilter;
import lite.crud.config.security.filter.SysUsernamePasswordAuthenticationFilter;
import lite.crud.domain.cdata.role.dto.RoleInfoPermissionQueryDto;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import lite.crud.infrastructure.persistence.redis.RedisInvokeInfrastructure;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author xl-9527
 * @since 2024/9/3
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    private final String[] permitAllGET = {};
    private final String[] permitAllPORT = {"/sys/login", "/admin/login/sign-out"};
    private final String[] permitAllAll = {};

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http, final RedisInvokeInfrastructure<LoginUserInfoVo> redisInvokeInfrastructure,
                                           final DelegatingAuthenticationEntryPoint basicAuthenticationEntryPoint,
                                           final SysUsernamePasswordAuthenticationFilter sysUsernamePasswordAuthenticationFilter
    ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, permitAllPORT).permitAll()
                        .requestMatchers(HttpMethod.GET, permitAllGET).permitAll()
                        .requestMatchers(permitAllAll).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(heads -> heads.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin).cacheControl(HeadersConfigurer.CacheControlConfig::disable))
                .addFilterBefore(sysUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new SysTokenFilter(redisInvokeInfrastructure), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e ->
                        e.authenticationEntryPoint(basicAuthenticationEntryPoint)
                                .accessDeniedHandler(new SysAccessDeniedHandler())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(final UserDetailsService userDetailsService, final PasswordEncoder passwordEncoder) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public DelegatingAuthenticationEntryPoint basicAuthenticationEntryPoint() {
        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
        entryPoints.put(new RequestHeaderRequestMatcher(
                "X-Requested-With", "XMLHttpRequest"), new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        DelegatingAuthenticationEntryPoint defaultEntryPoint = new DelegatingAuthenticationEntryPoint(entryPoints);
        defaultEntryPoint.setDefaultEntryPoint(new SysBasicAuthenticationEntryPoint());
        return defaultEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * dynamic refresh authentication information from database
     */
    @Component
    public static class DynamicRefreshAuthentication {

        private final RoleInfoService roleInfoService;
        private final RoleInfoPermissionService roleInfoPermissionService;

        public DynamicRefreshAuthentication(final RoleInfoService roleInfoService, final RoleInfoPermissionService roleInfoPermissionService) {
            this.roleInfoService = roleInfoService;
            this.roleInfoPermissionService = roleInfoPermissionService;
        }

        public List<RoleInfoPermissionVo> doGetPermissionInfo(final RoleInfoVo roleInfoVo) {
            return roleInfoPermissionService.queryListPage(
                    new RoleInfoPermissionQueryDto(roleInfoVo)
            ).getRecord();
        }

        public List<RoleInfoVo> doGetRoleInfo(final LocalDateTime updateTime) {
            return roleInfoService.queryListPage(
                    new RoleInfoQueryDto(updateTime)
            ).getRecord();
        }

        public List<RoleInfoVo> exec() {
            final List<RoleInfoVo> roleInfoVos = this.doGetRoleInfo(LocalDateTime.now());
            if (ObjectUtils.isNotEmpty(roleInfoVos)) {
                for (final RoleInfoVo roleInfoVo : roleInfoVos) {
                    final List<RoleInfoPermissionVo> roleInfoPermissionVos = this.doGetPermissionInfo(roleInfoVo);

                }
            }
            return null;
        }
    }
}
