package hu.schonherz.training.landing.web.mapper;

import hu.schonherz.training.landing.vo.RoleVo;
import hu.schonherz.training.landing.vo.UserVo;
import hu.schonherz.training.landing.vo.remote.RemoteUserVo;

import java.util.List;

public final class RemoteUserMapper {

    private RemoteUserMapper() {
    }

    public static RemoteUserVo toRemoteUser(UserVo userVo, List<RoleVo> roles) {
        if (userVo == null) {
            return null;
        }

        return new RemoteUserVo(userVo.getId(),
                                userVo.getName(),
                                userVo.getEmail(),
                                userVo.isActive(),
                                RemoteRoleMapper.toRemoteRoleList(roles));
    }

}
