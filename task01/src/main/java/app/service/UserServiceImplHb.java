package app.service;

import app.dao.UserDaoImplHb;
import app.model.User;

import java.util.List;

public class UserServiceImplHb implements UserService {

    @Override
    public void create(User user) {
        if (!validate(user)) {
            UserDaoImplHb.getInstance().create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return UserDaoImplHb.getInstance().readAll();
    }

    @Override
    public void updateUser(User user) {
        if (getById(user.getId()) != null) {
            UserDaoImplHb.getInstance().update(user);
        }
    }

    @Override
    public void delete(long id) {
        UserDaoImplHb.getInstance().delete(id);
    }

    @Override
    public User getById(long id) {
        return UserDaoImplHb.getInstance().getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = UserDaoImplHb.getInstance().getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }
}
