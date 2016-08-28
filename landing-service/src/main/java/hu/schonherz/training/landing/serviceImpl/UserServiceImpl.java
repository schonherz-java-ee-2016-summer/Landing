package hu.schonherz.training.landing.serviceImpl;

import hu.schonherz.training.landing.core.repository.UserRepository;
import hu.schonherz.training.landing.mapper.UserMapper;
import hu.schonherz.training.landing.service.UserService;
import hu.schonherz.training.landing.vo.UserVo;
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
    public void createUser(UserVo userVo) {
        userRepository.save(UserMapper.toEntity(userVo));
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