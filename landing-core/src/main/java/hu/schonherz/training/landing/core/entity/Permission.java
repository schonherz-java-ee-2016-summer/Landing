package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 343200021345763772L;

    @Basic
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "permission_role", joinColumns = {
            @JoinColumn(name = "permission_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false) })
    private List<Role> roles;

    public Permission() {
        super();
    }

    public Permission(String name, List<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
