package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private String email;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    @Column(nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles;

    public User() {
        this.active = true;
    }

    public User(String name, String email, String password, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = true;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                "} ";
    }
}
