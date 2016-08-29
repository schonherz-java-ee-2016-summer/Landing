package hu.schonherz.training.landing.vo;

import java.io.Serializable;
import java.util.List;

public class GroupVo implements Serializable{

    private static final long serialVersionUID = 367418928505763772L;

    private Long id;
    private String name;
    private List<UserVo> users;

    public GroupVo() {
    }

    public GroupVo(Long id, String name, List<UserVo> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public List<UserVo> getUsers() {
        return users;
    }

    public void setUsers(List<UserVo> users) {
        this.users = users;
    }
}
