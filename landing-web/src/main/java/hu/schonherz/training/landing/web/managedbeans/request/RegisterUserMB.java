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

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPassword = bCryptPasswordEncoder.encode(user.getUser().getPassword());

        usr = userService.getUserByName(user.getUser().getName());

        if (!user.getUser().getPassword().equals(user.getConfirmPassword())) {
            LOGGER.warn("Passwords do not match!");
            return "register";
        }
        if (usr != null) {
            LOGGER.warn(user.getUser().getName() + " user already exists!");
            return "register";
        }

        user.getUser().setPassword(encPassword);

        userService.createUser(user.getUser());

        LOGGER.info(user.getUser().getName() + " registered with " + user.getUser().getEmail() + " email address!");

        return "200";
    }

    public UserMB getUser() {
        return user;
    }

    public void setUser(UserMB user) {
        this.user = user;
    }
}
