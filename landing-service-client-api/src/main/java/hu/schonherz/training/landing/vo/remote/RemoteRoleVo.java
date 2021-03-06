package hu.schonherz.training.landing.vo.remote;

import java.io.Serializable;

public class RemoteRoleVo implements Serializable {

    private Long id;
    private String name;

    public RemoteRoleVo() {
    }

    public RemoteRoleVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        RemoteRoleVo that = (RemoteRoleVo) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RemoteRoleVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
