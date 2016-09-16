package hu.schonherz.training.landing.service.impl;

import hu.schonherz.training.landing.core.entity.Role;
import hu.schonherz.training.landing.core.entity.User;
import hu.schonherz.training.landing.core.repository.RoleRepository;
import hu.schonherz.training.landing.core.repository.UserRepository;
import hu.schonherz.training.landing.service.mapper.RoleMapper;
import hu.schonherz.training.landing.service.mapper.UserMapper;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
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
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserVo> getUsers() {
        return UserMapper.toVo(userRepository.findAll());
    }

    @Override
    public UserVo saveUser(UserVo userVo) {
        return UserMapper.toVo(userRepository.save(UserMapper.toEntity(userVo)));
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
        userRepository.findByName(name).getRoles().add(RoleMapper.toEntity(roleVo));
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

}
