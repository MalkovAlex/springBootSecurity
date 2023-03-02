package spring.boot_security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.boot_security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean add(Role role) {
        entityManager.persist(role);
        return true;
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public Role getRole(String userRole) {
        return entityManager.createQuery("select role from Role role where role.name =: name", Role.class)
                .setParameter("name", userRole).getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }


    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
