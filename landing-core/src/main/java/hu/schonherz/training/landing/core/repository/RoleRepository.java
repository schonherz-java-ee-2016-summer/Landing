package hu.schonherz.training.landing.core.repository;

import hu.schonherz.training.landing.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /*void removeRoleFromUser(Long roleId, Long userId);*/

    Role findByName(String name);

    @Query("SELECT r FROM User u JOIN u.roles r WHERE u.id = ?1")
    List<Role> findRolesByUserId(Long userId);
}
