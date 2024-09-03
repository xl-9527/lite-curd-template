package lite.crud.config.security;

import lite.crud.application.handler.user.UserInfoService;
import lite.crud.domain.user.dto.UserInfoQueryDto;
import lite.crud.domain.user.vo.UserInfoVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.GroupManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/3
 */
@Service
public class SysUserDetailService implements UserDetailsService, GroupManager {

    private final UserInfoService userInfoService;

    public SysUserDetailService(final UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final List<UserInfoVo> userInfo = userInfoService.getUserInfo(new UserInfoQueryDto(username, null));
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userInfo.get(0).toLoginUserInfoVo();
    }

    /*  ------------------- group  ↓ ------------------- */

    @Override
    public List<String> findAllGroups() {
        return null;
    }

    @Override
    public List<String> findUsersInGroup(final String groupName) {
        return null;
    }

    @Override
    public void createGroup(final String groupName, final List<GrantedAuthority> authorities) {

    }

    @Override
    public void deleteGroup(final String groupName) {

    }

    @Override
    public void renameGroup(final String oldName, final String newName) {

    }

    @Override
    public void addUserToGroup(final String username, final String group) {

    }

    @Override
    public void removeUserFromGroup(final String username, final String groupName) {

    }

    @Override
    public List<GrantedAuthority> findGroupAuthorities(final String groupName) {
        return null;
    }

    @Override
    public void addGroupAuthority(final String groupName, final GrantedAuthority authority) {

    }

    @Override
    public void removeGroupAuthority(final String groupName, final GrantedAuthority authority) {

    }
}
