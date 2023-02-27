package spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot_security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
