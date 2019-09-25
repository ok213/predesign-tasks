package app.service;

import app.model.User;

import java.util.List;

public interface UserServiceInterface {

    void create(User user);
    List<User> getAll();
    void updateUser(User user, String[] params);
    void delete(String[] params);
    User getById(String id);
    boolean validate(User user);

}
