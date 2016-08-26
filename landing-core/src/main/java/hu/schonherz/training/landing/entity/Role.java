package hu.schonherz.training.landing.entity;

import javax.persistence.*;

/**
 * Created on 2016.08.26..
 */

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