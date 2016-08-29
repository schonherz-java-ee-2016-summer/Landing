package hu.schonherz.training.landing.service;

import hu.schonherz.training.landing.vo.UserVo;

import java.util.List;

public interface UserService {

    void createUser(UserVo userVo);

    UserVo getUserById(Long id);

    UserVo getUserByName(String name);

    List<UserVo> getUsers();

    List<UserVo> getUsers(Integer page, Integer size);

    Long countUsers();

    void updateUser(UserVo userVo);

}
