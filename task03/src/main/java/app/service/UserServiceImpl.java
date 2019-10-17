package app.service;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void create(User user) {
        if (!validate(user)) {
            userDAO.create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return userDAO.readAll();
    }

    @Override
    public void update(User user) {
        if (getById(user.getId()) != null) {
            userDAO.update(user);
        }
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = userDAO.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDAO.getByLogin(login);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.disabled(false);
            builder.password(user.getPassword());
            String[] authorities = user.getAuthorities()
                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);

            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
