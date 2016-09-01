package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Group")
public class Group extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    public Group() {
        super();
    }

    public Group(String name) {
        this.name = name;
        //this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }
}
