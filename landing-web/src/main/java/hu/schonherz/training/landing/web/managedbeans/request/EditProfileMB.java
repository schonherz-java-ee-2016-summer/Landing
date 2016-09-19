package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "editProfile")
@RequestScoped
public class EditProfileMB {

    @EJB
    private UserService userService;

    private UserVo user;

    @PostConstruct
    private void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);
    }

    public String saveUser() {
        userService.saveUser(user);
        return "200";
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
