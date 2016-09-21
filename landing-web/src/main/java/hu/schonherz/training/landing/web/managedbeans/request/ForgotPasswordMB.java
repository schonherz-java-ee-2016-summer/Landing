package hu.schonherz.training.landing.web.managedbeans.request;

import com.sun.mail.util.MailConnectException;
import hu.schonherz.training.landing.service.MailService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.service.exception.EmailSendingException;
import hu.schonherz.training.landing.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;

@ManagedBean(name = "forgotPassword")
@RequestScoped
public class ForgotPasswordMB {

    @EJB
    private UserService userService;

    private String email;

    @EJB
    private MailService mailService;

    public String sendNewPassword() {
        UserVo user = userService.getUserByEmail(email);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("forgotPassword.email.notFound.summary"),
                    bundle.getString("forgotPassword.email.notFound.detail")));
            return "error";
        }

        String newPassword = UUID.randomUUID().toString();
        newPassword = newPassword.substring(0, 8);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPassword = encoder.encode(newPassword);
        user.setPassword(encPassword);
        userService.saveUser(user);
        try {
            mailService.sendMail("noreply@javatraining.hu", user.getEmail(), "Your new password is: ", newPassword);
        } catch (EmailSendingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("forgotPassword.sendMail.error.summary"),
                    bundle.getString("forgotPassword.sendMail.error.detail")));
            return "error";
        }

        return "home";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
