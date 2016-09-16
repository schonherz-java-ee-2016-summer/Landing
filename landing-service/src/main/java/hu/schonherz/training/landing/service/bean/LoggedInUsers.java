package hu.schonherz.training.landing.service.bean;

import hu.schonherz.training.landing.vo.remote.RemoteUserVo;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public class LoggedInUsers {

    private HashMap<String, RemoteUserVo> usersMap;

    @PostConstruct
    public void init() {
        usersMap = new HashMap<>();
    }

    public HashMap<String, RemoteUserVo> getUsersMap() {
        return usersMap;
    }
}
