package app.dao;

import app.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private Connection connection;

    public UserDaoImpl() {
        try {
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/task01");
            if (dataSource != null) {
                connection = dataSource.getConnection();
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void create(User user) {
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO users(login, password, name) VALUES(?, ?, ?);")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> readAll() {
        List<User> usersList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            // language=MYSQL
            statement.executeQuery("SELECT * FROM users;");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void update(long id, String[] params) {
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE users SET login=?, password=?, name=? WHERE id=?;")) {
            preparedStatement.setString(1, params[0]);
            preparedStatement.setString(2, params[1]);
            preparedStatement.setString(3, params[2]);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE from users WHERE id=?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?;")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user = new User(resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("name"));
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
