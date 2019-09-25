package app.dao;

import app.model.User;

import java.util.List;

// определяет абстрактный API, который выполняет операции CRUD над объектами типа User
public interface UserDao {

    void create(User user);
    List<User> readAll();
    void update(String[] params);  // String[] params : [id, login, password, name]
    void delete(String id);

    User getById(String id);
    User getByLoginAndPassword(String login, String password);

}
