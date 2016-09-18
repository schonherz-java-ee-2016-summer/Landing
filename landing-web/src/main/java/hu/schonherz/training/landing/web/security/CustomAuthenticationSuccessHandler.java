package hu.schonherz.training.landing.web.security;


import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import hu.schonherz.training.landing.vo.remote.RemoteUserVo;
import hu.schonherz.training.landing.web.mapper.RemoteUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

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
        String cookieId = UUID.randomUUID().toString();

        userService.addLoggedInUser(cookieId, remoteUser);

        Cookie cookie = new Cookie("loggedInUser", cookieId);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        httpServletResponse.addCookie(cookie);
        LOGGER.info("cookie created " + cookie.getValue());
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home.xhtml");

    }

}
