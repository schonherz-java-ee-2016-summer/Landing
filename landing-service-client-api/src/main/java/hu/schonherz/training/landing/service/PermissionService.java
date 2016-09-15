package hu.schonherz.training.landing.service;


import hu.schonherz.training.landing.vo.PermissionVo;

import java.util.List;

public interface PermissionService {

    PermissionVo getPermissionById(Long id);

    PermissionVo getPermissionByName(String name);

    List<PermissionVo> getPermissions();

    List<PermissionVo> getPermissionsByRoleId(Long roleId);

}
