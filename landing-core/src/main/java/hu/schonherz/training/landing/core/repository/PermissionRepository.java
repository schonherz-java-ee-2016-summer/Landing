package hu.schonherz.training.landing.core.repository;

import hu.schonherz.training.landing.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);

    List<Permission> getAllPermission();

}
