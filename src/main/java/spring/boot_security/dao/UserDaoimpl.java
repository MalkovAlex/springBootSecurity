package spring.boot_security.dao;


import org.springframework.stereotype.Repository;
import spring.boot_security.model.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getList() {
        return entityManager.createQuery("from User", User.class).getResultList();

    }

    @Override
    public void updateUser(Long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery(
                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();

    }

    @Override
    public void deleteUser(User user) {
        entityManager.joinTransaction();
        findUser(user).forEach(u -> entityManager.remove(u.getId()));
    }

    @Override
    public List<User> findUser(User user) {
        TypedQuery<User> query = entityManager.createQuery("from User where username=:nm and lastName=:lnm and email=:eml", User.class);
        query.setParameter("nm", user.getUsername());
        query.setParameter("lnm", user.getLastName());
        query.setParameter("eml", user.getEmail());
        return query.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u left join fetch u.roles where u.username=:username", User.class)
                .setParameter("username", username).getSingleResult();
    }


}
