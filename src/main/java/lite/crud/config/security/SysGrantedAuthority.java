package lite.crud.config.security;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author xl-9527
 * @since 2024/9/5
 */
@Setter
@NoArgsConstructor
public class SysGrantedAuthority implements GrantedAuthority {

    public SysGrantedAuthority(final String authority) {
        this.authority = authority;
    }

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public String toString() {
        return "SysGrantedAuthority{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
