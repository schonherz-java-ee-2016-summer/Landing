package hu.schonherz.training.landing.impl;

import hu.schonherz.training.landing.core.repository.PermissionRepository;
import hu.schonherz.training.landing.mapper.PermissionMapper;
import hu.schonherz.training.landing.service.PermissionService;
import hu.schonherz.training.landing.vo.PermissionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(name = "PermissionService", mappedName = "PermissionService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class PermissionServiceImpl implements PermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PermissionVo getPermissionById(Long id) {
        return PermissionMapper.toVo(permissionRepository.findOne(id));
    }

    @Override
    public PermissionVo getPermissionByName(String name) {
        return PermissionMapper.toVo(permissionRepository.findByName(name));
    }

    @Override
    public List<PermissionVo> getPermissions() {
        return PermissionMapper.toVo(permissionRepository.findAll());
    }

    @Override
    public List<PermissionVo> getPermissionsByRoleId(Long roleId) {
        return PermissionMapper.toVo(permissionRepository.findPermissionsByRoleId(roleId));
    }

}