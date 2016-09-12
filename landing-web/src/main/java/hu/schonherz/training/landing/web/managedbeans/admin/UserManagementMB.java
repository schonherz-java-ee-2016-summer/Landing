package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "userManagement")
public class UserManagementMB {

    @EJB
    private UserService userService;

    private DualListModel<String> users;

    @PostConstruct
    public void init() {
        List<String> usersSource = new ArrayList<String>();
        List<String> usersTarget = new ArrayList<String>();

        for (UserVo userVo : userService.getUsers()) {
            usersSource.add(userVo.getName());
        }

        users = new DualListModel<String>(usersSource, usersTarget);
    }

    public DualListModel<String> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<String> users) {
        this.users = users;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((String) item)).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
}