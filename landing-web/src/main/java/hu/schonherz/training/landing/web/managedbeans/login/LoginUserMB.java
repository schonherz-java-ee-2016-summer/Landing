package hu.schonherz.training.landing.web.managedbeans.login;

import hu.schonherz.training.landing.vo.UserVo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "loginUser")
@SessionScoped
public class LoginUserMB implements Serializable {

    private UserVo user = new UserVo();
    private boolean loggedIn = false;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
