package hu.schonherz.training.landing.service;

import hu.schonherz.training.landing.vo.PermissionVo;
import hu.schonherz.training.landing.vo.RoleVo;

import java.util.List;

public interface RoleService {

    RoleVo getRoleById(Long id);

    RoleVo getRoleByName(String name);

    List<RoleVo> getRoles();

    List<RoleVo> getRolesByUserId(Long userId);

    void addPermissionToRoleByName(String name, PermissionVo PermissionVo);

}
