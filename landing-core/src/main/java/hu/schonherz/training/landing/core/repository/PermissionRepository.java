package hu.schonherz.training.landing.core.repository;

import hu.schonherz.training.landing.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);

    @Query("SELECT p FROM Role r JOIN r.permissions p WHERE r.id = ?1")
    List<Permission> findPermissionsByRoleId(Long roleId);
}
