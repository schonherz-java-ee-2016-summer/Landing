package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.vo.RoleVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roleManagement")
public class RoleManagementMB {

    @EJB
    private RoleService roleService;

    private List<RoleVo> userRoles = new ArrayList<RoleVo>();
    private List<RoleVo> permRoles = new ArrayList<RoleVo>();
    private String userRole;
    private String permRole;

    @PostConstruct
    public void init() {
        userRoles.addAll(roleService.getRoles());
        permRoles.addAll(roleService.getRoles());
    }

    public List<RoleVo> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleVo> userRoles) {
        this.userRoles = userRoles;
    }

    public List<RoleVo> getPermRoles() {
        return permRoles;
    }

    public void setPermRoles(List<RoleVo> permRoles) {
        this.permRoles = permRoles;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPermRole() {
        return permRole;
    }

    public void setPermRole(String permRole) {
        this.permRole = permRole;
    }
}
