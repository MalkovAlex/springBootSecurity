package spring.boot_security.dao;


import spring.boot_security.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    User getUser(Long id);

    List<User> getList();

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void deleteUser(User user);

    List<User> findUser(User user);

}
