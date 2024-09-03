package lite.crud.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

/**
 * @author xl-9527
 * @since 2024/9/3
 */
public class SysBasicAuthenticationFilter extends BasicAuthenticationFilter {

    public SysBasicAuthenticationFilter(final AuthenticationManager authenticationManager, final DelegatingAuthenticationEntryPoint basicAuthenticationEntryPoint) {
        super(authenticationManager, basicAuthenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
    }

    @Override
    protected void doFilterNestedErrorDispatch(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        super.doFilterNestedErrorDispatch(request, response, filterChain);
    }
}
