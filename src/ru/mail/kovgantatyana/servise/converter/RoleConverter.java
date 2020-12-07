package ru.mail.kovgantatyana.servise.converter;

import ru.mail.kovgantatyana.repository.model.UserRole;

public class RoleConverter {

    public static UserRole userRoleCreator(String role, int userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        switch (role){
            case "WRITER":
                userRole.setRoleId(1);
                break;
            case "READER":
                userRole.setRoleId(2);
                break;
            case "JOURNALIST":
                userRole.setRoleId(3);
                break;
        }
        return userRole;
    }
}
