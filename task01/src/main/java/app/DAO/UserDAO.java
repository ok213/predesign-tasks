package app.DAO;

import app.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean validateUser(User user) {
        boolean validateResult = false;
        try {
            // language=MYSQL
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT count(*) FROM users WHERE login=? AND password=?;");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            validateResult = resultSet.getInt(1) == 1 ? true : false;
            resultSet.close();
            preparedStatement.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validateResult;
    }

    public void createUser(User user) {
        try {
            // language=MYSQL
            PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO users(login, password, name) VALUES(?, ?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            // language=MYSQL
            stmt.executeQuery("SELECT * FROM users;");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong("id");
                String login = result.getString("login");
                String password = result.getString("password");
                String name = result.getString("name");
                User user = new User(id, login, password, name);
                usersList.add(user);
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;

    }


}
