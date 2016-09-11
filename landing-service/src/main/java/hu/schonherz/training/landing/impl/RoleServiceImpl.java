package hu.schonherz.training.landing.impl;

import hu.schonherz.training.landing.core.repository.RoleRepository;
import hu.schonherz.training.landing.mapper.RoleMapper;
import hu.schonherz.training.landing.service.RoleService;
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
    public List<RoleVo> getRoles() {
        return RoleMapper.toVo(roleRepository.findAll());
    }

    @Override
    public List<RoleVo> getRolesByUserId(Long userId) {
        return RoleMapper.toVo(roleRepository.findRolesByUserId(userId));
    }

}
