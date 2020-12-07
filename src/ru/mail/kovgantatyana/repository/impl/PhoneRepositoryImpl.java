package ru.mail.kovgantatyana.repository.impl;

import ru.mail.kovgantatyana.repository.ConnectionService;
import ru.mail.kovgantatyana.repository.PhoneRepository;
import ru.mail.kovgantatyana.repository.model.Phone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepositoryImpl implements PhoneRepository {
    private static PhoneRepositoryImpl instanse;
    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private PhoneRepositoryImpl() {
    }

    public static PhoneRepositoryImpl getInstanse() {
        if (instanse == null) {
            instanse = new PhoneRepositoryImpl();
        }
        return instanse;
    }

    @Override
    public List<Phone> getPhoneListByUser(int userId) {
        Connection connection = null;
        try {
            List<Phone> fhoneList = new ArrayList<>();
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM t_fhone JOIN t_user ON t_user.F_USERID=t_fhone.F_USERID WHERE t_user.F_USERID=?")) {
                    statement.setInt(1, userId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        fhoneList.add(Phone.newBuilder()
                                .id(resultSet.getInt("F_FHONEID"))
                                .userId(resultSet.getInt("F_USERID"))
                                .number(resultSet.getString("F_FHONENUMBER"))
                                .build()
                        );
                    }
                    return fhoneList;
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
    public void savePhones(List<Phone> phones) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                for (Phone phone : phones) {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO t_fhone (F_USERID, F_FHONENUMBER) VALUES (?,?)")) {
                        statement.setInt(1, phone.getUserId());
                        statement.setString(2, phone.getNumber());
                        statement.executeQuery();
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
