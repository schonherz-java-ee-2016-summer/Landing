package hu.schonherz.training.landing.service.impl;

import hu.schonherz.training.landing.service.client.api.UserService;
import hu.schonherz.training.landing.service.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 2016.08.26..
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {


    @Override
    public List<UserVo> getAllUser() {
        return null;
    }

    @Override
    public UserVo getUserById(Long id) {
        return null;
    }

    @Override
    public UserVo getUserByName(String name) {
        return null;
    }

    @Override
    public List<UserVo> getAllUser(Integer page, Integer size) {
        return null;
    }

    @Override
    public Long countUser() {
        return null;
    }
}
