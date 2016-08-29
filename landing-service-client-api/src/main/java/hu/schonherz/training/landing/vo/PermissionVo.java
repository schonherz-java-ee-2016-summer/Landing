package hu.schonherz.training.landing.vo;

import java.io.Serializable;
import java.util.List;

public class PermissionVo implements Serializable {

    private static final long serialVersionUID = 564578928505763772L;

    private Long id;
    private String name;
    private List<RoleVo> roles;

    public PermissionVo() {
    }

    public PermissionVo(Long id, String name, List<RoleVo> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVo> roles) {
        this.roles = roles;
    }
}
