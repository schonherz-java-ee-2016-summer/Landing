package hu.schonherz.training.landing.service.client.api;

import hu.schonherz.training.landing.service.vo.UserVo;

import java.util.List;

public interface UserService {

    List<UserVo> getUsers();

    UserVo getUserById(Long id);

    UserVo getUserByName(String name);

    List<UserVo> getUsers(Integer page, Integer size);

    Long countUsers();

}
