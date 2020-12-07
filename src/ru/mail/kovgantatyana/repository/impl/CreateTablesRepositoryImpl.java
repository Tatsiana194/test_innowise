package ru.mail.kovgantatyana.repository.impl;

import ru.mail.kovgantatyana.repository.ConnectionService;
import ru.mail.kovgantatyana.repository.CreateTablesRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesRepositoryImpl implements CreateTablesRepository {
    private static CreateTablesRepositoryImpl instance;

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private CreateTablesRepositoryImpl() {
    }

    public static CreateTablesRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CreateTablesRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void createUserTable() {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP TABLE t_user");
                    statement.executeUpdate("CREATE TABLE t_user(F_USERID INT(255) PRIMARY KEY AUTO_INCREMENT,F_USERNAME VARCHAR(255)NOT NULL, F_USERSURNAME VARCHAR(255)NOT NULL, F_EMAIL VARCHAR(255)NOT NULL)");
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
    public void createRoleTable() {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP TABLE t_role");
                    statement.executeUpdate("CREATE TABLE t_role(F_ROLEID INT(255) PRIMARY KEY AUTO_INCREMENT, F_ROLENAME VARCHAR(255) NOT NULL ) ");
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
    public void createPhoneTable() {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP TABLE t_fhone");
                    statement.executeUpdate("CREATE TABLE t_fhone(F_FHONEID INT(255) PRIMARY KEY AUTO_INCREMENT, F_USERID INT(255),F_FHONENUMBER VARCHAR (255) NOT NULL) ");
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
    public void createUserRoleTable() {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP TABLE t_user_x_role");
                    statement.executeUpdate("CREATE TABLE t_user_x_role(F_USERID INT(255), F_ROLEID INT(255))");
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
