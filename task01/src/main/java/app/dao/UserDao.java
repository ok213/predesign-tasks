package app.dao;

import app.model.User;

import java.util.List;

// определяет абстрактный API, который выполняет операции CRUD над объектами типа User
public interface UserDao {

    void create(User user);
    List<User> readAll();
    void update(User user);
    void delete(long id);

    User getById(long id);
    User getByLoginAndPassword(String login, String password);

}
