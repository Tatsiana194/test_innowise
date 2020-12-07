package ru.mail.kovgantatyana.servise.validator;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {
    public List<String> checkRole(List<String> roles) {
        List<String> errors = new ArrayList<>();

        if (roles.size() > 3) {
            errors.add("user can have only three role");
        }

        for (String role : roles) {
            if (role.equalsIgnoreCase("WRITER") |
                    role.equalsIgnoreCase("READER") |
                    role.equalsIgnoreCase("JOURNALIST")) {
                return errors;
            } else {
                errors.add("role must be writer, reader, journalist");
            }
        }
        return errors;
    }
}
