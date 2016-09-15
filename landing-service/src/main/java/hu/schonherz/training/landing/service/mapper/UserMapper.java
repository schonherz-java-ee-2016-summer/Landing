package hu.schonherz.training.landing.service.mapper;

import hu.schonherz.training.landing.core.entity.User;
import hu.schonherz.training.landing.vo.UserVo;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static UserVo toVo(User userEntity) {
        if (userEntity == null) {
            return null;
        }

        LOGGER.info(userEntity.getName());
        LOGGER.info(userEntity.getEmail());
        LOGGER.info(String.valueOf(userEntity.isActive()));
        //LOGGER.info("Data ", userEntity.getName(), userEntity.getEmail(), userEntity.isActive());
        LOGGER.info("User entity mapped to UserVo", userEntity);
        return mapper.map(userEntity, UserVo.class);
    }

    public static User toEntity(UserVo userVo) {
        if (userVo == null) {
            return null;
        }

        LOGGER.info("UserVo mapped to User entity", userVo);
        return mapper.map(userVo, User.class);
    }

    public static List<UserVo> toVo(List<User> users) {
        return users.stream()
                                    .map(UserMapper::toVo)
                                    .collect(Collectors.toList());
    }

    public static List<User> toEntity(List<UserVo> userVos) {
        return userVos.stream()
                                    .map(UserMapper::toEntity)
                                    .collect(Collectors.toList());
    }

}
