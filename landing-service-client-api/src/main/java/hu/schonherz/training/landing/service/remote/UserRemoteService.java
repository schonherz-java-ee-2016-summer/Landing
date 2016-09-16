package hu.schonherz.training.landing.service.remote;

import hu.schonherz.training.landing.vo.remote.RemoteUserVo;

public interface UserRemoteService {

    RemoteUserVo getLoggedInUser(String cookie);

}
