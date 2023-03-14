package spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.boot_security.dao.UserDao;
import spring.boot_security.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Пользователь не найден: "+username);
        }
        return user;
    }
}
