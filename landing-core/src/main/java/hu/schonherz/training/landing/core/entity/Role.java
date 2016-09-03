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

    @ManyToMany(fetch = FetchType.LAZY)
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