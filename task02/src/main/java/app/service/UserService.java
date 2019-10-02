package app.service;

import app.model.User;

import java.util.List;

public interface UserService {

    void create(User user);
    List<User> getAll();
    void update(User user);
    void delete(long id);

    User getById(long id);
    boolean validate(User user);

}
