package ru.mail.kovgantatyana.repository.impl;

import ru.mail.kovgantatyana.repository.ConnectionService;
import ru.mail.kovgantatyana.repository.UserRepository;
import ru.mail.kovgantatyana.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl instance;
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User getUserByUserName(String username) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT F_USERID, F_USERNAME, F_USERSURNAME, F_EMAIL FROM t_user WHERE F_USERNAME = ?")) {
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return User.newBuilder()
                                .id(resultSet.getInt("F_USERID"))
                                .name(resultSet.getString("F_USERNAME"))
                                .surname(resultSet.getString("F_USERSURNAME"))
                                .email(resultSet.getString("F_EMAIL"))
                                .build();
                    }
                } catch (SQLException e) {
                    System.out.println((e.getMessage()));
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public List<User> getUserList() {
        Connection connection = null;
        try {
            List<User> users = new ArrayList<>();
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM t_user")) {
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        users.add(User.newBuilder()
                                .id(resultSet.getInt("F_USERID"))
                                .name(resultSet.getString("F_USERNAME"))
                                .surname(resultSet.getString("F_USERSURNAME"))
                                .email(resultSet.getString("F_EMAIL"))
                                .build()
                        );
                    }
                    return users;
                } catch (SQLException e) {
                    System.out.println((e.getMessage()));
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }


    @Override
    public int saveUser(User user) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_user (F_USERNAME, F_USERSURNAME, F_EMAIL) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getSurname());
                    statement.setString(3, user.getEmail());
                    statement.executeUpdate();
                    ResultSet resultSet = statement.getGeneratedKeys();
                    resultSet.next();
                    int userId = resultSet.getInt(1);
                    return userId;
                } catch (SQLException e) {
                    System.out.println((e.getMessage()));
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public void deleteUser(int userId) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statementRole = connection.prepareStatement("DELETE FROM t_user_x_role WHERE F_USERID=?");
                     PreparedStatement statementFhone = connection.prepareStatement("DELETE FROM t_fhone WHERE F_USERID=?");
                     PreparedStatement statementUser = connection.prepareStatement("DELETE FROM t_user WHERE F_USERID=?")) {
                    statementRole.setInt(1, userId);
                    statementRole.executeUpdate();
                    statementFhone.setInt(1, userId);
                    statementFhone.executeUpdate();
                    statementUser.setInt(1, userId);
                    statementUser.executeUpdate();
                } catch (SQLException e) {
                    System.out.println((e.getMessage()));
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateUser(User user, int userId) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("UPDATE t_user SET F_USERNAME=?, F_USERSURNAME=?, F_EMAIL=? WHERE F_USERID=?")) {
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getSurname());
                    statement.setString(3, user.getEmail());
                    statement.setInt(4, userId);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println((e.getMessage()));
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
