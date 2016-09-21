package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@ManagedBean(name = "editProfile")
@RequestScoped
public class EditProfileMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileMB.class);

    @EJB
    private UserService userService;

    private UserVo user;

    @PostConstruct
    private void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
    }

    public String saveUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        userService.saveUser(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                bundle.getString("editProfile.success"),
                bundle.getString("editProfile.success")));
        return "200";
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
