package hu.schonherz.training.landing.impl;

import hu.schonherz.training.landing.core.entity.Permission;
import hu.schonherz.training.landing.core.entity.Role;
import hu.schonherz.training.landing.core.entity.User;
import hu.schonherz.training.landing.core.repository.RoleRepository;
import hu.schonherz.training.landing.core.repository.UserRepository;
import hu.schonherz.training.landing.mapper.RoleMapper;
import hu.schonherz.training.landing.mapper.UserMapper;
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

    @Autowired
    private UserRepository userRepository;

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

}
