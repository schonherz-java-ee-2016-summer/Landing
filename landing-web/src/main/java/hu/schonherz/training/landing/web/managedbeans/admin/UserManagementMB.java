package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "userManagement")
public class UserManagementMB {

    @EJB
    private UserService userService;

    private DualListModel<UserVo> users;
    private DualListModel<UserVo> usersPass;

    private UserVo user;

    @PostConstruct
    public void init() {
        List<UserVo> usersSource = new ArrayList<UserVo>();
        List<UserVo> usersTarget = new ArrayList<UserVo>();
        List<UserVo> usersPassSource = new ArrayList<UserVo>();
        List<UserVo> usersPassTarget = new ArrayList<UserVo>();

        usersSource.addAll(userService.getUsers());
        usersPassSource.addAll(userService.getUsers());

        users = new DualListModel<UserVo>(usersSource, usersTarget);
        usersPass = new DualListModel<UserVo>(usersPassSource, usersPassTarget);
    }

    public DualListModel<UserVo> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<UserVo> users) {
        this.users = users;
    }

    public DualListModel<UserVo> getUsersPass() {
        return usersPass;
    }

    public void setUsersPass(DualListModel<UserVo> usersPass) {
        this.usersPass = usersPass;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
