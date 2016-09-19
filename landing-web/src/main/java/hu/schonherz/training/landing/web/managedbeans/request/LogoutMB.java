package hu.schonherz.training.landing.web.managedbeans.request;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "logout")
@RequestScoped
public class LogoutMB {

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/home.xhtml?logout=succes";
    }

}
