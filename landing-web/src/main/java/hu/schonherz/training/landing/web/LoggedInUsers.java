package hu.schonherz.training.landing.web;

import hu.schonherz.training.landing.vo.remote.RemoteUserVo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
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
