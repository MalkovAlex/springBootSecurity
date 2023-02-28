package spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot_security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
}
