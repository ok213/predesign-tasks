package app.service;

import app.dao.UserDaoImpl;
import app.model.User;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {

    }

    private boolean validate(User user) {
        User userFromBase = getUserDaoImpl().getByLoginAndPassword(user.getLogin(), user.getPassword());
        return user.equals(userFromBase);
    }

    public void create(User user) {
        // если такого пользователя нет, то создаем
        if (!validate(user)) {
            getUserDaoImpl().create(user);
        }
    }

    public List<User> getUsers() {
        return getUserDaoImpl().readAll();
    }

    public void delete(User user) {
        if (validate(user)) {
            long id = getUserDaoImpl().getByLoginAndPassword(user.getLogin(), user.getPassword()).getId();
            getUserDaoImpl().delete(id);
        }
    }

    // String[] params : [login, password, name]
    public void updateUser(User user, String[] params) {
        if (validate(user)) {
            long id = getUserDaoImpl().getByLoginAndPassword(user.getLogin(), user.getPassword()).getId();
            getUserDaoImpl().update(id, params);
        }
    }

    private static Connection getMySqlConnection() {
        Connection connection = null;
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/task01");
            if (dataSource != null) {
                connection = dataSource.getConnection();
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static UserDaoImpl getUserDaoImpl() {
        return new UserDaoImpl(getMySqlConnection());
    }
}
