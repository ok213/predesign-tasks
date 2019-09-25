package app.service;

import app.dao.UserDaoImpl;
import app.model.User;

import java.util.List;

public class UserService implements UserServiceInterface{

    private UserDaoImpl userDaoImpl;

    public UserService() {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public void create(User user) {
        // если такого пользователя нет, то создаем
        if (!validate(user)) {
            userDaoImpl.create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return userDaoImpl.readAll();
    }

    // String[] params : [id, login, password, name]
    @Override
    public void updateUser(User user, String[] params) {
        if (validate(user)) {
             userDaoImpl.update(params);
        }
    }

    // String[] params : [id, login, password]
    @Override
    public void delete(String[] params) {
        if (validate(new User(params[1], params[2], ""))) {
            userDaoImpl.delete(params[0]);
        }
    }

    @Override
    public User getById(String id) {
        return userDaoImpl.getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = userDaoImpl.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }

}
