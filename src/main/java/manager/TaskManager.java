package manager;

import db.DBConnectionProvider;
import model.Status;
import model.Task;
import model.User;
import model.UserType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private TaskManager taskManager = new TaskManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private Connection connection;

    public TaskManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addTask(Task task) {
        try {
            String query = "INSERT INTO `task` (`name`,`description`,`user_id`,`status`,`deadline`) " +
                    "VALUES(?,?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, task.getName());
            pStatement.setString(2, task.getDescription());
            pStatement.setInt(3, task.getUser().getId());
            pStatement.setString(4, String.valueOf(task.getStatus()));
            pStatement.setString(5, sdf.format(task.getDeadline()));
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                task.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public void addTask(Task task) {
//        try {
//            Statement statement = connection.createStatement();
//            String query = String.format("INSERT INTO TASK(name,description,status,deadline)VALUES('%s','%s','%s','%s');",
//                    task.getName(), task.getDescription(), task.getStatus(), task.getDeadline());
//            statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                int id = generatedKeys.getInt(1);
//                task.setId(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public List<Task> getAllTasks() {
        String sql = "SELECT* from task";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = null;
                try {
                    task = Task.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .description(resultSet.getString(3))
                            .status(Status.valueOf(resultSet.getString(4)))
                            .deadline(resultSet.getString(6) == null ? null : sdf.parse(resultSet.getString(3)))
                            .build();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                result.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            try {
                return Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .status(Status.valueOf(resultSet.getString(4)))
                        .deadline(resultSet.getString(5) == null ? null : sdf.parse(resultSet.getString(3)))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Task getTasksByName(String name) {
        String sql = "SELECT * FROM user WHERE name =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getTaskFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public Task getTasksById(int id) {
//        String sql = "SELECT * FROM task where id=" + id;
//
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            if (resultSet.next()) {
//                return Task.builder()
//                        .id(resultSet.getInt(1))
//                        .name(resultSet.getString(2))
//                        .description(resultSet.getString(3))
//                        .status(Status.valueOf(resultSet.getString(5)))
//                        .deadline(resultSet.getString(6) == null ? null : sdf.parse(resultSet.getString(3)))
//                        .build();
//
//            }
//        } catch (SQLException | ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
