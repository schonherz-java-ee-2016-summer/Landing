package hu.schonherz.training.landing.mapper;

import hu.schonherz.training.landing.core.entity.Role;
import hu.schonherz.training.landing.vo.RoleVo;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    private static Mapper mapper = new DozerBeanMapper();

    public static RoleVo toVo(Role userEntity) {
        if (userEntity == null) {
            return null;
        }

        //LOGGER.info("Data ", userEntity.getName(), userEntity.getEmail(), userEntity.isActive());
        //LOGGER.info("User entity mapped to UserVo", userEntity);
        return mapper.map(userEntity, RoleVo.class);
    }

    public static List<RoleVo> toVo(List<Role> roles) {
        return roles.stream()
                .map(RoleMapper::toVo)
                .collect(Collectors.toList());
    }
}
