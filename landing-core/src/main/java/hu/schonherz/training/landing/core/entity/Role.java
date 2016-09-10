package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permission",
                    joinColumns = @JoinColumn(name = "role_id"),
                    inverseJoinColumns = @JoinColumn(name = "permissions_id"))
    private List<Permission> permissions;

    public Role() {
        super();
    }

    public Role(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Role role = (Role) o;

        if (getId() != null ? !getId().equals(role.getId()) : role.getId() != null) {
            return false;
        }

        if (getName() != null ? !getName().equals(role.getName()) : role.getName() != null) {
            return false;
        }
        return getPermissions() != null ? getPermissions().equals(role.getPermissions()) : role.getPermissions() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPermissions() != null ? getPermissions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}