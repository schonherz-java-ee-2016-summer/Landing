package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.PermissionService;
import hu.schonherz.training.landing.vo.PermissionVo;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "permissionManagement")
public class PermissionManagementMB {

    @EJB
    private PermissionService permissionService;

    private DualListModel<String> permissions;
    private String permission;

    @PostConstruct
    public void init() {
        List<String> permissionsSource = new ArrayList<String>();
        List<String> permissionsTarget = new ArrayList<String>();

        for (PermissionVo permissionVo : permissionService.getPermissions()) {
            permissionsSource.add(permissionVo.getName());
        }

        permissions = new DualListModel<String>(permissionsSource, permissionsTarget);
    }

    public DualListModel<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(DualListModel<String> permissions) {
        this.permissions = permissions;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
