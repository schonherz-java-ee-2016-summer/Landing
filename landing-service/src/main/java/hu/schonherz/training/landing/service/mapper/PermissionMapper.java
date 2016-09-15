package hu.schonherz.training.landing.service.mapper;

import hu.schonherz.training.landing.core.entity.Permission;
import hu.schonherz.training.landing.vo.PermissionVo;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static PermissionVo toVo(Permission permission) {

        if (permission == null) {
            return null;
        }

        return mapper.map(permission, PermissionVo.class);
    }

    public static Permission toEntity(PermissionVo permissionVo) {

        if (permissionVo == null) {
            return null;
        }

        LOGGER.info("PermissionVo mapped to Permission entity", permissionVo);
        return mapper.map(permissionVo, Permission.class);
    }

    public static List<PermissionVo> toVo(List<Permission> permissions) {
        return permissions.stream()
                .map(PermissionMapper::toVo)
                .collect(Collectors.toList());
    }
}
