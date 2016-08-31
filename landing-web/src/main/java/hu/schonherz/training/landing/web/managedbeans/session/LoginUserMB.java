package hu.schonherz.training.landing.web.managedbeans.session;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "login")
@SessionScoped
public class LoginUserMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserMB.class);

    @ManagedProperty(value = "#{sUserBean}")
    private SessionUserMB user;

    @EJB
    private UserService userService;

    public String doLogin() {

        UserVo usr;
        try {
            usr = userService.getUserByName(user.getUser().getName());

            if (usr == null) {
                LOGGER.error("Wrong username!");
                return "401";
            }

            if (!BCrypt.checkpw(user.getUser().getPassword(), usr.getPassword())) {
                LOGGER.error("Wrong password!");
                return "401";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Something bad happened!");
            return "400";
        }

        LOGGER.info(user.getUser().getName() + " logged in!");
        return "200";
    }

    public SessionUserMB getUser() {
        return user;
    }

    public void setUser(SessionUserMB user) {
        this.user = user;
    }
}