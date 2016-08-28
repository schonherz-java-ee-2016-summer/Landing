package hu.schonherz.training.landing.managedbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created on 2016.08.28..
 */

@ManagedBean(name = "user")
@RequestScoped
public class UserMB {
    @ManagedProperty(value = "#{name}")
    private String name;

    @ManagedProperty(value = "#{email}")
    private String email;

    @ManagedProperty(value = "#{password}")
    private String password;

    public String add() {
        return "400";
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
}
