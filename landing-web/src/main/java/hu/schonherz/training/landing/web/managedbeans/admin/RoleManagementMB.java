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

    private List<String> roles = new ArrayList<String>();
    private String role;

    @PostConstruct
    public void init() {
        for (RoleVo roleVo : roleService.getRoles()) {
            roles.add(roleVo.getName());
        }
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
