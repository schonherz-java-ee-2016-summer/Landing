package hu.schonherz.training.landing.managedbeans;

import hu.schonherz.training.landing.vo.UserVo;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserMB {

    private UserVo user = new UserVo();

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
