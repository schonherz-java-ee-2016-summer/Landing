package hu.schonherz.training.landing.web.mapper;

import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.remote.RemoteRoleVo;

import java.util.List;
import java.util.stream.Collectors;

public final class RemoteRoleMapper {

    private RemoteRoleMapper() {
    }

    public static List<RemoteRoleVo> toRemoteRoleList(List<RoleVo> roleVos) {
        if (roleVos == null) {
            return null;
        }

        return roleVos.stream()
                        .map(RemoteRoleMapper::toRemoteRole)
                        .collect(Collectors.toList());
    }

    public static RemoteRoleVo toRemoteRole(RoleVo roleVo) {
        if (roleVo == null) {
            return null;
        }

        return new RemoteRoleVo(roleVo.getId(), roleVo.getName());
    }

}
