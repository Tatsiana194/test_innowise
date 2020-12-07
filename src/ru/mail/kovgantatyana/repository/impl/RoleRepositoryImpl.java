package ru.mail.kovgantatyana.repository.impl;

import ru.mail.kovgantatyana.repository.ConnectionService;
import ru.mail.kovgantatyana.repository.RoleRepository;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    private static RoleRepositoryImpl instanse;
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private RoleRepositoryImpl() {
    }

    public static RoleRepositoryImpl getInstanse() {
        if (instanse == null) {
            instanse = new RoleRepositoryImpl();
        }
        return instanse;
    }

    @Override
    public List<Role> getRoleListByUser(int userId) {
        Connection connection = null;
        try {
            List<Role> roleList = new ArrayList<>();
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM t_role JOIN t_user_x_role ON t_user_x_role.F_ROLEID=t_role.F_ROLEID WHERE t_user_x_role.F_USERID = ?")) {
                    statement.setInt(1, userId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        roleList.add(Role.newBuilder()
                                .id(resultSet.getInt("F_ROLEID"))
                                .role(resultSet.getString("F_ROLENAME"))
                                .build()
                        );
                    }
                    return roleList;
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
    public void saveRoles(List<UserRole> userRoleList) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                for (UserRole userRole : userRoleList) {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_user_x_role (F_USERID, F_ROLEID) VALUES (?,?)")) {
                        statement.setInt(1, userRole.getUserId());
                        statement.setInt(2, userRole.getRoleId());
                        ResultSet resultSet = statement.executeQuery();
                    } catch (SQLException e) {
                        System.out.println((e.getMessage()));
                        e.printStackTrace();
                    }
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
