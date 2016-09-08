package hu.schonherz.training.landing.web.managedbeans.login;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "login")
@RequestScoped
public class LoginMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginMB.class);

    @ManagedProperty(value = "#{loginUser}")
    private LoginUserMB loginUser;

    @EJB
    private UserService userService;

    public String doLogin() {

        UserVo usr;

        try {
            usr = userService.getUserByName(loginUser.getUser().getName());

            if (usr == null) {
                LOGGER.warn("Wrong username!");
                return "login";
            }

            if (!BCrypt.checkpw(loginUser.getUser().getPassword(), usr.getPassword())) {
                LOGGER.warn("Wrong password!");
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Something bad happened!");
            return "400";
        }

        loginUser.setLoggedIn(true);
        LOGGER.info(loginUser.getUser().getName() + " logged in!");
        return "200";
    }

    public LoginUserMB getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUserMB loginUser) {
        this.loginUser = loginUser;
    }
}