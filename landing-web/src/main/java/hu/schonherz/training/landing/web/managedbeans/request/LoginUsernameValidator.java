package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class LoginUsernameValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUsernameValidator.class);

    @EJB
    private UserService userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {
        if (submittedValue == null) {
            return; // Let required="true" handle.
        }

        String username = submittedValue.toString();
        UserVo dbUser = userService.getUserByName(username);

        if (dbUser == null) {
            LOGGER.warn("\"" + username + "\"" + " username does not exist!");
            throw new ValidatorException(new FacesMessage("Username does not exist"));
        }
    }

}
