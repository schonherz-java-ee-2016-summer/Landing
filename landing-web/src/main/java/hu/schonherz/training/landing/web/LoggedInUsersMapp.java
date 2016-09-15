package hu.schonherz.training.landing.web;

import hu.schonherz.training.landing.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.Map;

@Startup
@Singleton
public class LoggedInUsersMapp {

    private static HashMap<String, UserVo> usersMap;

    @PostConstruct
    public void init(){
        usersMap = new HashMap<>();
    }

    public Map<String, UserVo> getUsersMap() {
        return usersMap;
    }

}
