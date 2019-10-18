package app.service;

import app.model.Authorities;
import app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void create(User user);
    List<User> getAll();
    void update(User user);
    void delete(long id);

    User getById(long id);
    boolean validate(User user);
}
