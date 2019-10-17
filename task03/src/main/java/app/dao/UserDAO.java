package app.dao;

import app.model.User;

import java.util.List;

public interface UserDAO {

    void create(User user);
    List<User> readAll();
    void update(User user);
    void delete(long id);

    User getById(long id);
    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);
}
