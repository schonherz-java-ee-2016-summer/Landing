package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
@NamedQueries({
    @NamedQuery(name = "findRoleByName", query = "select r from Role r where r.name= :roleName") })
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
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
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}