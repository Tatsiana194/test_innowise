package ru.mail.kovgantatyana.repository;

import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.UserRole;

import java.util.List;

public interface RoleRepository {
    List<Role> getRoleListByUser(int userId);

    void saveRoles(List<UserRole> userRoleList);
}
