package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.MailService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.UUID;

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

            String newPassword = UUID.randomUUID().toString();
            newPassword = newPassword.substring(0, 8);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encPassword = bCryptPasswordEncoder.encode(newPassword);
            userVo.setPassword(encPassword);
            userService.saveUser(userVo);
            mailService.sendMail("noreply@javatraining.hu", userVo.getEmail(), "Your new password is: ", newPassword);
        }
    }
}
