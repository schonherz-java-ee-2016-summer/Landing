package hu.schonherz.training.landing.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserVo implements Serializable {

    private static final long serialVersionUID = 5932000328505763772L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;

    public UserVo(Long id, String name, String email, String password, boolean active, List<RoleVo> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public UserVo() {
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

}
