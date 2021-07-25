package manager;

import db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }
    public void addUser(User user) {
        try {
            String query = "INSERT INTO `user` (`name`,`surname`,`email`,`password`,`user_type`) " +
                    "VALUES(?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, user.getName());
            pStatement.setString(2, user.getSurname());
            pStatement.setString(3, user.getEmail());
            pStatement.setString(4, user.getPassword());
            pStatement.setString(5, String.valueOf(user.getUserType()));
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


//    public void addUser(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String query = String.format("INSERT INTO USER(name,surname,email,password,user_type)VALUES('%s','%s','%s','%s','%s'));",
//                    user.getName(), user.getSurname(), user.getEmail(), user.getPassword(),user.getUserType());
//            statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                int id = generatedKeys.getInt(1);
//                user.setId(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public User getById(int id) {
        String sql = "SELECT * FROM user where id=" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUsersByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email='" + email
                + " ' and password ='" + password + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .userType(UserType.valueOf(resultSet.getString(6)))
                        .build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<User> getAllUsers() {
        String sql = "SELECT* from user";
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .userType(UserType.valueOf(resultSet.getString(6)))
                        .build();
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .userType(UserType.valueOf(resultSet.getString(6)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
