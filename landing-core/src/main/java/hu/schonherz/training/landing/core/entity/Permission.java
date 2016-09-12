package hu.schonherz.training.landing.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Permissions")
public class Permission extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    public Permission() {
        super();
    }

    public Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Permission that = (Permission) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                '}';
    }
}
