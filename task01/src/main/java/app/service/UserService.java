package app.service;

import app.dao.UserDaoImpl;
import app.model.User;

import java.util.List;

public class UserService {

    private UserDaoImpl userDaoImpl;

    public UserService() {
        userDaoImpl = new UserDaoImpl();
    }

    public void create(User user) {
        // если такого пользователя нет, то создаем
        if (!validate(user)) {
            userDaoImpl.create(user);
        }
    }

    public List<User> getAll() {
        return userDaoImpl.readAll();
    }

    // String[] params : [login, password, name]
    public void updateUser(User user, String[] params) {
        if (validate(user)) {
            // нужно, чтобы у User был всегда логин и пароль
            if ("".equals(params[0])) {
                params[0] = user.getLogin();
            }
            if ("".equals(params[1])) {
                params[1] = user.getPassword();
            }

            long id = userDaoImpl.getByLoginAndPassword(user.getLogin(), user.getPassword()).getId();
             userDaoImpl.update(id, params);
        }
    }

    public void delete(User user) {
        if (validate(user)) {
            long id = userDaoImpl.getByLoginAndPassword(user.getLogin(), user.getPassword()).getId();
            userDaoImpl.delete(id);
        }
    }

    private boolean validate(User user) {
        User userFromBase = userDaoImpl.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }

}
