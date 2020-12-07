package ru.mail.kovgantatyana.servise;

import ru.mail.kovgantatyana.repository.model.Phone;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.repository.model.UserRole;

import java.util.List;

public interface GetFromFile {
    List<Phone> getPhoneFromFile();

    List<User> getUserFromFile();

    List<Role> getRoleFromFile();

    List<UserRole> getUserRoleFromFile();
}
