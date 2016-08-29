package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Group")
public class Group extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "group_user", joinColumns = {
            @JoinColumn(name = "group_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) })
    private List<User> users;

    public Group() {
        super();
    }

    public Group(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
