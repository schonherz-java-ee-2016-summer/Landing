package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.RoleVo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;

@ManagedBean(name = "addRoleToUsers")
@RequestScoped
public class AddRoleToUsersMB {

    @EJB
    private UserService userService;
    @EJB
    private RoleService roleService;

    public void addRole(List<String> userNames, String role) {
        RoleVo roleVo = roleService.getRoleByName(role);

        for (String name : userNames) {
            userService.addRoleToUserByName(name, roleVo);
        }
    }

    public void removeRole(List<String> userNames, String role) {
        RoleVo roleVo = roleService.getRoleByName(role);

        for (String name : userNames) {
            userService.removeRoleFromUserByName(name, roleVo);
        }
    }
}
