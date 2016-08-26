package hu.schonherz.training.landing.repository;

import hu.schonherz.training.landing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2016.08.26..
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RoleRepository extends JpaRepository<Role, Long> {

    void removeRoleFromUser(Long roleId, Long userId);

    Role findByName(String name);

}
