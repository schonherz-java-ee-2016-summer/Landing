package hu.schonherz.training.landing.web.managedbeans.request;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginMB {

    private String username;

    private String password;

    public String login() throws ServletException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest)externalContext.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) externalContext.getRequest(), (ServletResponse) externalContext.getResponse());
        facesContext.responseComplete();
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}