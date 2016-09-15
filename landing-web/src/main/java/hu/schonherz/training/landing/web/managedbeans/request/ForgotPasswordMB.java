package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.landing.service.MailService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.util.UUID;

@ManagedBean(name = "forgotPassword")
@RequestScoped
public class ForgotPasswordMB {

    @EJB
    private UserService userService;

    private String email;

    @EJB
    private MailService mailService;

    public String sendNewPassword(){
        UserVo user = userService.getUserByEmail(email);

        if (user == null) {
            return "forgotPassword";
        }

        String newPassword = UUID.randomUUID().toString();
        newPassword = newPassword.substring(0, 8);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encPassword);
        userService.saveUser(user);
        mailService.sendMail("noreply@javatraining.hu", user.getEmail(), "Your new password is: ", newPassword);

        return "home";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
