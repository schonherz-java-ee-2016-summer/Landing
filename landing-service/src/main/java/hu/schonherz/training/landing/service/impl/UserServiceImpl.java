package hu.schonherz.training.landing.service.impl;

import hu.schonherz.training.landing.core.repository.UserRepository;
import hu.schonherz.training.landing.service.client.api.UserService;
import hu.schonherz.training.landing.service.mapper.UserMapper;
import hu.schonherz.training.landing.service.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserVo> getUsers() {
        return UserMapper.toVo(userRepository.findAll());
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
    public List<UserVo> getUsers(Integer page, Integer size) {
        // TODO
        return null;
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }
}
