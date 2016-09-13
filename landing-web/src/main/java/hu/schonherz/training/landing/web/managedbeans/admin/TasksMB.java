package hu.schonherz.training.landing.web.managedbeans.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "tasks")
public class TasksMB {

    private int task;

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }
}