package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roleManagement")
public class RoleManagementMB {

    @EJB
    private RoleService roleService;

    private List<String> userRoles = new ArrayList<String>();
    private List<String> permRoles = new ArrayList<String>();
    private String userRole;
    private String permRole;

    @PostConstruct
    public void init() {
        for (RoleVo roleVo : roleService.getRoles()) {
            userRoles.add(roleVo.getName());
            permRoles.add(roleVo.getName());
        }
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public List<String> getPermRoles() {
        return permRoles;
    }

    public void setPermRoles(List<String> permRoles) {
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
