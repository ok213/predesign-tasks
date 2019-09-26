package app.service;

import app.dao.UserDaoImpl;
import app.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDaoImpl;

    public UserServiceImpl() {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public void create(User user) {
        if (!validate(user)) {
            userDaoImpl.create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return userDaoImpl.readAll();
    }

    @Override
    public void updateUser(User user) {
        if (getById(user.getId()) != null) {
             userDaoImpl.update(user);
        }
    }

    @Override
    public void delete(long id) {
        userDaoImpl.delete(id);
    }

    @Override
    public User getById(long id) {
        return userDaoImpl.getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = userDaoImpl.getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }

}
