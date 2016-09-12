package hu.schonherz.training.landing.web.managedbeans.register;

import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterMB.class);

    @ManagedProperty(value = "#{registerUser}")
    private RegisterUserMB user;

    @EJB
    private UserService userService;
    @EJB
    private RoleService roleService;

    public String doRegister() {
        
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPassword = bCryptPasswordEncoder.encode(user.getUser().getPassword());
        user.getUser().setPassword(encPassword);
        userService.registerUser(user.getUser());

        LOGGER.info(user.getUser().getName() + " registered with " + user.getUser().getEmail() + " email address!");

        return "200";
    }

    public RegisterUserMB getUser() {
        return user;
    }

    public void setUser(RegisterUserMB user) {
        this.user = user;
    }
}
