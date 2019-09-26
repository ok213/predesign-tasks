package app.dao;

import app.model.User;
import app.util.DBHelperJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private Connection connection;

    public UserDaoImpl() {
        connection = DBHelperJDBC.getConnection();
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
    public void update(User user) {
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE users SET login=?, password=?, name=? WHERE id=?;")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        User user = null;
        // language=MYSQL
        try (PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user = new User(id, resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("name"));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
