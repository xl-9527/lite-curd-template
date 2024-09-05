package lite.crud.domain.sys.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
@Getter
@Setter
public class LoginUserInfoVo implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    public LoginUserInfoVo() {
    }

    public LoginUserInfoVo(final Integer id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    private Integer id;

    /**
     * 用户名字
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
}
