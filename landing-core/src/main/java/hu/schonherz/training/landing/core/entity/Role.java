package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@NamedQueries({
    @NamedQuery(name = "findRoleByName", query = "select r from Role r where r.name= :roleName") })
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}