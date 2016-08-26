package hu.schonherz.training.landing.core.repository;

import hu.schonherz.training.landing.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleRepository extends JpaRepository<Role, Long> {
    void removeRoleFromUser(Long roleId, Long userId) throws Exception;

}
