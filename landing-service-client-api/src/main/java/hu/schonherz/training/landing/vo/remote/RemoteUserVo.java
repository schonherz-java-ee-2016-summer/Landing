package hu.schonherz.training.landing.vo.remote;

import hu.schonherz.training.landing.vo.RoleVo;

import java.io.Serializable;
import java.util.List;

public class RemoteUserVo implements Serializable {

    private Long id;
    private String name;
    private String email;
    private boolean active;
    private List<RemoteRoleVo> roles;

    public RemoteUserVo() {
    }

    public RemoteUserVo(Long id, String name, String email, boolean active, List<RemoteRoleVo> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RemoteRoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RemoteRoleVo> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RemoteUserVo that = (RemoteUserVo) o;

        if (isActive() != that.isActive()) {
            return false;
        }
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
            return false;
        }
        return getRoles() != null ? getRoles().equals(that.getRoles()) : that.getRoles() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RemoteUserVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }

}
