package hu.schonherz.training.landing.web.validator;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@FacesValidator
@ManagedBean(name = "usernameValidator")
@RequestScoped
public class RegisterUsernameValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUsernameValidator.class);

    @EJB
    private UserService userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {
        String username = submittedValue.toString();
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Landing", context.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Landing", Locale.ENGLISH);
        }

        if (username.length() < 3) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("usernameValidator.length.summary"),
                    "\"" + username + "\"" + bundle.getString("usernameValidator.length.detail")));
        }

        LOGGER.info(username);
        UserVo dbUser = userService.getUserByName(username);

        if (dbUser != null) {
            LOGGER.warn("\"" + username + "\"" + " username already in use!");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("usernameValidator.exists.summary"),
                     bundle.getString("usernameValidator.prefix") + " \"" + username + "\" " + bundle.getString("usernameValidator.exists.detail")));
        }
    }

}
