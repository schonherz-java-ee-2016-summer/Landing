package hu.schonherz.training.landing.web.managedbeans.login;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class LoginPasswordValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPasswordValidator.class);

    @ManagedProperty(value = "#{loginUser}")
    private LoginUserMB user;

    @EJB
    private UserService userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {

        String password = submittedValue.toString();
        UserVo dbUser = userService.getUserByName(user.getUser().getName());

        if (dbUser != null) {
            if (!BCrypt.checkpw(password, dbUser.getPassword())) {
                LOGGER.warn("Wrong password given for user: " + "\"" + user.getUser().getName() + "\"!");
                throw new ValidatorException(new FacesMessage("Wrong password"));
            }
        }
    }

    public LoginUserMB getUser() {
        return user;
    }

    public void setUser(LoginUserMB user) {
        this.user = user;
    }
}
