package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.MailService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.service.exception.EmailSendingException;
import hu.schonherz.training.landing.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name = "setPasswordToDefault")
@RequestScoped
public class SetPasswordToDefaultMB {

    @EJB
    private UserService userService;

    @EJB
    private MailService mailService;

    public void setDefault(List<String> userNames) {
        for (String name : userNames) {
            UserVo userVo = userService.getUserByName(name);

            ResourceBundle bundle;
            try {
                bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            } catch (MissingResourceException e) {
                bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            }

            String newPassword = UUID.randomUUID().toString();
            newPassword = newPassword.substring(0, 8);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encPassword = bCryptPasswordEncoder.encode(newPassword);
            userVo.setPassword(encPassword);
            userService.saveUser(userVo);
            try {
                mailService.sendMail("noreply@javatraining.hu", userVo.getEmail(), "Your new password is: ", newPassword);
            } catch (EmailSendingException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        bundle.getString("admin.resetPassword.error.summary"),
                        bundle.getString("admin.resetPassword.error.detail")));
            }
        }
    }
}
