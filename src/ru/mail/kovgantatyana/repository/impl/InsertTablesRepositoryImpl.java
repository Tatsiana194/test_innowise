package ru.mail.kovgantatyana.repository.impl;

import ru.mail.kovgantatyana.repository.ConnectionService;
import ru.mail.kovgantatyana.repository.InsertTablesRepository;
import ru.mail.kovgantatyana.repository.model.Phone;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.repository.model.UserRole;
import ru.mail.kovgantatyana.servise.model.PhoneDTO;
import ru.mail.kovgantatyana.servise.model.RoleDTO;
import ru.mail.kovgantatyana.servise.model.UserDTO;
import ru.mail.kovgantatyana.servise.model.UserRoleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InsertTablesRepositoryImpl implements InsertTablesRepository {
    private static InsertTablesRepositoryImpl instance;
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private InsertTablesRepositoryImpl() {
    }

    public static InsertTablesRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InsertTablesRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void insertUserTable(List<User> userList) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_user(F_USERID, F_USERNAME, F_USERSURNAME, F_EMAIL) VALUES (?,?,?,?)")) {
                    for (User user : userList) {
                        statement.setInt(1, user.getId());
                        statement.setString(2, user.getName());
                        statement.setString(3, user.getSurname());
                        statement.setString(4, user.getEmail());
                        statement.executeUpdate();
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
    }

    @Override
    public void insertRoleTable(List<Role> roleList) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_role(F_ROLEID, F_ROLENAME) VALUES (?,?)")) {
                    for (Role role : roleList) {
                        statement.setInt(1, role.getId());
                        statement.setString(2, role.getRole());
                        statement.executeUpdate();
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
    }

    @Override
    public void insertPhoneTable(List<Phone> phoneList) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_fhone (F_FHONEID, F_USERID, F_FHONENUMBER) VALUES (?,?,?)")) {
                    for (Phone fhone : phoneList) {
                        statement.setInt(1, fhone.getId());
                        statement.setInt(2, fhone.getUserId());
                        statement.setString(3, fhone.getNumber());
                        statement.executeUpdate();
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
    }

    @Override
    public void insertUserRoleTable(List<UserRole> userRoleList) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_user_x_role (F_USERID, F_ROLEID) VALUES (?,?)")) {
                    for (UserRole userRole : userRoleList) {
                        statement.setInt(1, userRole.getUserId());
                        statement.setInt(2, userRole.getRoleId());
                        statement.executeUpdate();
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
    }
}
