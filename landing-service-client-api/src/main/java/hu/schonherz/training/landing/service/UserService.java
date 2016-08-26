package hu.schonherz.training.landing.service;

import hu.schonherz.training.landing.vo.UserVo;

import java.util.List;

/**
 * Created on 2016.08.26..
 */

public interface UserService {

    public List<UserVo> getAllUser();

    public UserVo getUserById(Long id);

    public UserVo getUserByName(String name);

    public List<UserVo> getAllUser(Integer page, Integer size);

    public Long countUser();
}
