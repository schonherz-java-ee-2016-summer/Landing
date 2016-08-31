package hu.schonherz.training.landing.web.managedbeans.session;

import hu.schonherz.training.landing.vo.UserVo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sUserBean")
@SessionScoped
public class SessionUserMB {

    private UserVo user = new UserVo();

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
