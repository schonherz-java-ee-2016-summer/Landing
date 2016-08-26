package hu.schonherz.training.landing.repository;

import hu.schonherz.training.landing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2016.08.26..
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

    Long countByUsername(String username);

}
