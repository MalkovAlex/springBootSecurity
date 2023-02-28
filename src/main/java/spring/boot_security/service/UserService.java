package spring.boot_security.service;



import spring.boot_security.model.User;

import java.util.List;

public interface UserService {


    User getUser(Long id);

    List<User> getList();

    void updateUser(Long id, User user);

    void createUser(User user);

    void deleteUser(Long id);

    void deleteUser(User user);

    List<User> findUser(User user);
}
