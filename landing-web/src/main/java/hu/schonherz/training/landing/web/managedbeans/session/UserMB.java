package hu.schonherz.training.landing.web.managedbeans.session;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserMB implements Serializable {

    /*
     *org.springframework.security.core.userdetails.User
     */
    private User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
