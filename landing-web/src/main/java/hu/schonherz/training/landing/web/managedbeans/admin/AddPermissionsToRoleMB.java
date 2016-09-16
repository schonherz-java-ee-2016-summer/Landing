package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.PermissionService;
import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.vo.PermissionVo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "addPermissionsToRole")
@RequestScoped
public class AddPermissionsToRoleMB {

    @EJB
    private PermissionService permissionService;
    @EJB
    private RoleService roleService;

    public void addPermissions(List<String> permissionNames, String role) {
        for (String name : permissionNames) {
            PermissionVo permissionVo = permissionService.getPermissionByName(name);
            roleService.addPermissionToRoleByName(role, permissionVo);
        }
    }
}
