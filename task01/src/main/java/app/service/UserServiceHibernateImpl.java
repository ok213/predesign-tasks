package app.service;

import app.dao.UserDaoHibernateImpl;
import app.model.User;

import java.util.List;

@Deprecated
public class UserServiceHibernateImpl implements UserService {

    @Override
    public void create(User user) {
        if (!validate(user)) {
            UserDaoHibernateImpl.getInstance().create(user);
        }
    }

    @Override
    public List<User> getAll() {
        return UserDaoHibernateImpl.getInstance().readAll();
    }

    @Override
    public void updateUser(User user) {
        if (getById(user.getId()) != null) {
            UserDaoHibernateImpl.getInstance().update(user);
        }
    }

    @Override
    public void delete(long id) {
        UserDaoHibernateImpl.getInstance().delete(id);
    }

    @Override
    public User getById(long id) {
        return UserDaoHibernateImpl.getInstance().getById(id);
    }

    @Override
    public boolean validate(User user) {
        User userFromBase = UserDaoHibernateImpl.getInstance().getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }
}
