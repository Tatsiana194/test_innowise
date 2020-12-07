package ru.mail.kovgantatyana.servise.validator;

import java.util.ArrayList;
import java.util.List;

public class EmailValidator {

    public List<String> checkEmail(String email) {
        List<String> errors = new ArrayList<>();

        if (email.indexOf('@') < 0) {
            errors.add("email must have @");
        }

        if (email.indexOf('@', '@')> 0) {
            errors.add(("email.must have only one @"));
        }

        if (email.indexOf('.') < 0) {
            errors.add("email must have .");
        }

        return errors;
    }
}
