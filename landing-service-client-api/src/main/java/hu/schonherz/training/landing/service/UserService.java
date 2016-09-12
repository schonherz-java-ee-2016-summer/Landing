package hu.schonherz.training.landing.service;

import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;

import java.util.List;

public interface UserService {

    UserVo saveUser(UserVo userVo);

    UserVo getUserById(Long id);

    UserVo getUserByName(String name);

    List<UserVo> getUsers();

    Long countUsers();

    void deleteUser(Long id);

    void addRoleToUser(Long userId, RoleVo roleVo);

    void registerUser(UserVo user);
}
