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

    private DualListModel<PermissionVo> permissions;
    private PermissionVo permission;

    @PostConstruct
    public void init() {
        List<PermissionVo> permissionsSource = new ArrayList<PermissionVo>();
        List<PermissionVo> permissionsTarget = new ArrayList<PermissionVo>();

        permissionsSource.addAll(permissionService.getPermissions());

        permissions = new DualListModel<PermissionVo>(permissionsSource, permissionsTarget);
    }

    public DualListModel<PermissionVo> getPermissions() {
        return permissions;
    }

    public void setPermissions(DualListModel<PermissionVo> permissions) {
        this.permissions = permissions;
    }

    public PermissionVo getPermission() {
        return permission;
    }

    public void setPermission(PermissionVo permission) {
        this.permission = permission;
    }
}
