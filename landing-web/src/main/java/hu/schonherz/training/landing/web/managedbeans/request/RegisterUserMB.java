package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterUserMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserMB.class);

    @ManagedProperty(value = "#{userBean}")
    private UserMB user;

    @EJB
    private UserService userService;

    public String doRegister() {
        UserVo usr;

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPassword = bCryptPasswordEncoder.encode(user.getUser().getPassword());
        user.getUser().setPassword(encPassword);

        usr = userService.getUserByName(user.getUser().getName());

        if (usr != null) {
            LOGGER.error(user.getUser().getName() + " user already exists!");
            return "400";
        }

        userService.createUser(user.getUser());

        LOGGER.info(user.getUser().getName() + " registered with " + user.getUser().getEmail() + " email adress!");

        return "200";
    }

    public UserMB getUser() {
        return user;
    }

    public void setUser(UserMB user) {
        this.user = user;
    }
}
