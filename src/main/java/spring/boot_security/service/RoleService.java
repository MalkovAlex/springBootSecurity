package spring.boot_security.service;

import spring.boot_security.model.Role;

import java.util.List;

public interface RoleService {

    public boolean add(Role role);
    List<Role> getAllRoles();

    Role getRole(String userRole);

    Role getRoleById(Long id);

    void addRole(Role role);
}
