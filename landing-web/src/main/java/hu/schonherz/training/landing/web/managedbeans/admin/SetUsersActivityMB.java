package hu.schonherz.training.landing.web.managedbeans.admin;

import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "setUsersActivity")
@RequestScoped
public class SetUsersActivityMB {

    @EJB
    private UserService userService;

    public void setActivity(List<UserVo> users, boolean activity) {
        for (UserVo userVo : users) {
            userService.setUserActivityByName(userVo.getName(), activity);
        }
    }
}
