package hu.schonherz.training.landing.managedbeans.request;

import hu.schonherz.training.landing.managedbeans.UserMB;
import hu.schonherz.training.landing.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterUserMB {

    @ManagedProperty(value = "#{userBean}")
    private UserMB user;

    @EJB
    private UserService userService;

    public String doRegister() {
        userService.createUser(user.getUser());
        return "400";
    }

    public UserMB getUser() {
        return user;
    }

    public void setUser(UserMB user) {
        this.user = user;
    }
}
