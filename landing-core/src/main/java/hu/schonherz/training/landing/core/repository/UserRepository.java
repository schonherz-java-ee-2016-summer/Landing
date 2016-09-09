package hu.schonherz.training.landing.core.repository;


import hu.schonherz.training.landing.core.entity.Role;
import hu.schonherz.training.landing.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    //List<Role> findRolesByName(String name);

    //List<User> getAllUser();

}
