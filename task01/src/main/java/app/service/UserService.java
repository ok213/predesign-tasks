package app.service;

import app.models.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    @Resource(lookup = "java:comp/env", name="jdbc/task01")
    private DataSource dataSource;

//    public UserService() {
//        try {
//            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
//            dataSource = (DataSource) ctx.lookup("jdbc/task01");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }

    public boolean validateUser(User user) {
        boolean validateResult = false;
        // language=MYSQL
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT count(*) FROM users WHERE login=? AND password=?;");) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            validateResult = resultSet.getInt(1) == 1 ? true : false;
            resultSet.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validateResult;
    }

    public void createUser(User user) {
        // language=MYSQL
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO users(login, password, name) VALUES(?, ?, ?);");) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            // language=MYSQL
            statement.executeQuery("SELECT * FROM users;");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                User user = new User(id, login, password, name);
                usersList.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;

    }

    public void deleteUser(User user) {
        // language=MYSQL
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE from users WHERE login=? AND password=? AND name=?;")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        // language=MYSQL
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?;");) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user = new User(resultSet.getLong("id"), resultSet.getString("login"),
                            resultSet.getString("password"), resultSet.getString("name"));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user, String login, String password, String name) {
        User userFromBase = getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (userFromBase != null) {
            login = "".equals(login) ? userFromBase.getLogin() : login;
            password = "".equals(password) ? userFromBase.getPassword() : password;
            name = "".equals(name) ? userFromBase.getName() : name;
            // language=MYSQL
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement =
                         connection.prepareStatement("UPDATE users SET login=?, password=?, name=? WHERE id=?;")) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setLong(4, userFromBase.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
