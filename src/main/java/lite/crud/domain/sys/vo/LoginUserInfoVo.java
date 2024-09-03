package lite.crud.domain.sys.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
@Getter
@Setter
public class LoginUserInfoVo implements Serializable, UserDetails {
    public LoginUserInfoVo() {
    }

    public LoginUserInfoVo(final Integer id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    private Integer id;
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
