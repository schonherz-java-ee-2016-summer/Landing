package hu.schonherz.training.landing.web.security;


import hu.schonherz.training.landing.service.PermissionService;
import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.PermissionVo;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@EJB(name = "UserService", beanInterface = UserService.class)
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @EJB
    UserService userService;
    @EJB
    RoleService roleService;
    @EJB
    PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        UserVo user;
        try {
            user = userService.getUserByName(username);

            if (user == null) {
                throw new UsernameNotFoundException(username);
            }

            List<RoleVo> roles = roleService.getRolesByUserId(user.getId());
            List<PermissionVo> permissions = new ArrayList<PermissionVo>();

            for (RoleVo role : roles) {
                permissions.addAll(permissionService.getPermissionsByRoleId(role.getId()));
            }
            List<GrantedAuthority> authorities = buildUserAuthority(permissions);

            return buildUserForAuthentication(user, authorities);

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        }

    }

    private User buildUserForAuthentication(UserVo user, List<GrantedAuthority> authorities) {
        return new User(user.getName(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<PermissionVo> userPermissions) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (PermissionVo userPermission : userPermissions) {
            setAuths.add(new SimpleGrantedAuthority(userPermission.getName()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

}