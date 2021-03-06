package hu.schonherz.training.landing.service.impl;

import hu.schonherz.training.landing.core.entity.Permission;
import hu.schonherz.training.landing.core.repository.RoleRepository;
import hu.schonherz.training.landing.service.mapper.PermissionMapper;
import hu.schonherz.training.landing.service.mapper.RoleMapper;
import hu.schonherz.training.landing.service.RoleService;
import hu.schonherz.training.landing.vo.PermissionVo;
import hu.schonherz.training.landing.vo.RoleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "RoleService", mappedName = "RoleService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleVo getRoleById(Long id) {
        return RoleMapper.toVo(roleRepository.findOne(id));
    }

    @Override
    public RoleVo getRoleByName(String name) {
        return RoleMapper.toVo(roleRepository.findByName(name));
    }

    @Override
    public List<RoleVo> getRoles() {
        return RoleMapper.toVo(roleRepository.findAll());
    }

    @Override
    public List<RoleVo> getRolesByUserId(Long userId) {
        return RoleMapper.toVo(roleRepository.findRolesByUserId(userId));
    }

    @Override
    public void addPermissionToRoleByName(String name, PermissionVo permissionVo) {
        boolean contains = false;

        for (Permission perm : roleRepository.findByName(name).getPermissions()) {
            contains = perm.getName().equals(permissionVo.getName());
            if (contains) {
                break;
            }
        }

        if (!contains) {
            roleRepository.findByName(name).getPermissions().add(PermissionMapper.toEntity(permissionVo));
        }
    }

    @Override
    public void removePermissionFromRoleByName(String name, PermissionVo permissionVo) {
        roleRepository.findByName(name).getPermissions().remove(PermissionMapper.toEntity(permissionVo));
    }

}
