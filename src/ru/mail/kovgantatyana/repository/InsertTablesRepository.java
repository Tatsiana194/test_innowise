package ru.mail.kovgantatyana.repository;

import ru.mail.kovgantatyana.repository.model.Phone;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.repository.model.UserRole;

import java.util.List;

public interface InsertTablesRepository {

    void insertUserTable(List<User> userList);

    void insertRoleTable(List<Role> roleList);

    void insertPhoneTable(List<Phone> phoneList);

    void  insertUserRoleTable(List<UserRole> userRoleList);
}
