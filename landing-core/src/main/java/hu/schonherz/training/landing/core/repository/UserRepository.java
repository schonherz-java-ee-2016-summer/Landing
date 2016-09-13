package hu.schonherz.training.landing.core.repository;


import hu.schonherz.training.landing.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByEmail(String email);

    //List<Role> findRolesByName(String name);

    //List<User> getAllUser();

}
