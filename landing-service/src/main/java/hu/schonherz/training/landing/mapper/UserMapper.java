package hu.schonherz.training.landing.mapper;

import hu.schonherz.training.landing.core.entity.User;
import hu.schonherz.training.landing.vo.UserVo;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private static Mapper mapper = new DozerBeanMapper();

    public static UserVo toVo(User userEntity) {
        if (userEntity == null) {
            return null;
        }
        return mapper.map(userEntity, UserVo.class);
    }

    public static User toEntity(UserVo userVo) {
        if (userVo == null) {
            return null;
        }
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
