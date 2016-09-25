package hu.schonherz.training.landing.service.impl;

import hu.schonherz.training.landing.core.entity.Role;
import hu.schonherz.training.landing.core.entity.User;
import hu.schonherz.training.landing.service.bean.LoggedInUsers;
import hu.schonherz.training.landing.core.repository.RoleRepository;
import hu.schonherz.training.landing.core.repository.UserRepository;
import hu.schonherz.training.landing.service.mapper.RoleMapper;
import hu.schonherz.training.landing.service.mapper.UserMapper;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.service.remote.UserRemoteService;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import hu.schonherz.training.landing.vo.remote.RemoteUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "UserService", mappedName = "UserService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(UserService.class)
@Remote(UserRemoteService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class UserServiceImpl implements UserService, UserRemoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String DEFAULT_USER_ROLE = "USER";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LoggedInUsers loggedInUsers;

    @Override
    public List<UserVo> getUsers() {
        return UserMapper.toVo(userRepository.findAll());
    }

    @Override
    public UserVo saveUser(UserVo userVo) {
        User userEntity = userRepository.findOne(userVo.getId());
        if (userEntity == null) {
            userEntity = new User();
        }
        UserMapper.toEntity(userVo, userEntity);
        return UserMapper.toVo(userRepository.save(userEntity));
    }

    @Override
    public UserVo getUserById(Long id) {
        return UserMapper.toVo(userRepository.findOne(id));
    }

    @Override
    public UserVo getUserByName(String name) {
        return UserMapper.toVo(userRepository.findByName(name));
    }

    @Override
    public UserVo getUserByEmail(String email) {
        return UserMapper.toVo(userRepository.findByEmail(email));
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void addRoleToUser(Long userId, RoleVo roleVo) {
        userRepository.findOne(userId).getRoles().add(RoleMapper.toEntity(roleVo));
    }

    @Override
    public void addRoleToUserByName(String name, RoleVo roleVo) {
        boolean contains = false;

        for (Role role : userRepository.findByName(name).getRoles()) {
            contains = role.getName().equals(roleVo.getName());
            if (contains) {
                break;
            }
        }

        if (!contains) {
            userRepository.findByName(name).getRoles().add(RoleMapper.toEntity(roleVo));
        }
    }

    @Override
    public void removeRoleFromUserByName(String name, RoleVo roleVo) {
        List<Role> newRoles = new ArrayList<Role>();

        for (Role role : userRepository.findByName(name).getRoles()) {
            if (!(role.getName().equals(roleVo.getName()))) {
                newRoles.add(role);
            }
        }
        userRepository.findByName(name).setRoles(newRoles);
    }

    @Override
    public void setUserActivityByName(String name, boolean activity) {
        userRepository.findByName(name).setActive(activity);
    }

    @Override
    public void addLoggedInUser(String cookie, RemoteUserVo remoteUserVo) {
         loggedInUsers.getUsersMap().put(cookie, remoteUserVo);
    }

    @Override
    public void deleteLoggedInUser(String cookie) {
        loggedInUsers.getUsersMap().remove(cookie);
    }

    @Override
    public void registerUser(UserVo user) {

        User userEntity = UserMapper.toEntity(user);
        if (userEntity.getRoles() == null) {
            userEntity.setRoles(new ArrayList<>(1));
        }

        addDefaultRole(userEntity);
        userRepository.save(userEntity);
    }

    private void addDefaultRole(User userEntity) {
        Role role = roleRepository.findByName(DEFAULT_USER_ROLE);
        userEntity.getRoles().add(role);
    }

    @Override
    public RemoteUserVo getLoggedInUser(String cookie) {
        return loggedInUsers.getUsersMap().get(cookie);
    }
}
