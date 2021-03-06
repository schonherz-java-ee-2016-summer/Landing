package hu.schonherz.training.landing.web.security;

import hu.schonherz.training.landing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @EJB
    private UserService userService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String hash = getHashFromCookies(httpServletRequest.getCookies());

        if (hash != null) {
            userService.deleteLoggedInUser(hash);
        }

        Cookie cookie = new Cookie("loggedInUser", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        
        LOGGER.info("cookie destroyed" + hash);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home.xhtml");
    }

    private String getHashFromCookies(Cookie[] cookies) {

        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("loggedInUser")) {

                    return cookie.getValue();

                }
            }
        }
        return null;
    }

}
