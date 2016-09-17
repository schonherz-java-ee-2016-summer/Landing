package hu.schonherz.training.landing.web;


import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.remote.RemoteUserVo;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import hu.schonherz.training.landing.web.mapper.RemoteRoleMapper;
import hu.schonherz.training.landing.web.mapper.RemoteUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @EJB
    private UserService userService;
    @EJB
    private RoleService roleService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserVo user = userService.getUserByName(userDetails.getUsername());
        List<RoleVo> roles = roleService.getRolesByUserId(user.getId());
        RemoteUserVo remoteUser = RemoteUserMapper.toRemoteUser(user, roles);
        //RemoteUserVo remoteUser = new RemoteUserVo(user.getId(), user.getName(), user.getEmail(), user.isActive(), RemoteRoleMapper.toRemoteRoleList(roles));
        String cookie = UUID.randomUUID().toString();

        userService.addLoggedInUser(cookie, remoteUser);

        setDefaultTargetUrl("/home.xhtml");
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }

}
