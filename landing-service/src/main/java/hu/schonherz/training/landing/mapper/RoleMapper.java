package hu.schonherz.training.landing.mapper;

import hu.schonherz.training.landing.core.entity.Role;
import hu.schonherz.training.landing.vo.RoleVo;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static RoleVo toVo(Role role) {

        if (role == null) {
            return null;
        }

        //LOGGER.info("Data ", userEntity.getName(), userEntity.getEmail(), userEntity.isActive());
        //LOGGER.info("User entity mapped to UserVo", userEntity);
        return mapper.map(role, RoleVo.class);
    }

    public static Role toEntity(RoleVo roleVo) {

        if (roleVo == null) {
            return null;
        }

        LOGGER.info("RoleVo mapped to Role entity", roleVo);
        return mapper.map(roleVo, Role.class);
    }

    public static List<RoleVo> toVo(List<Role> roles) {
        return roles.stream()
                .map(RoleMapper::toVo)
                .collect(Collectors.toList());
    }
}
