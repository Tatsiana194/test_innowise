package ru.mail.kovgantatyana.servise.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {

    public List<String> checkPhone(List<String> phones) {
        List<String> errors = new ArrayList<>();

        if (phones.size() > 3) {
            errors.add("user can have only three phone");
        }

        for (String phone : phones) {
            Pattern pattern = Pattern.compile("375[0-9]{9}");
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.find()) {
                errors.add("phone number must be in format 375XXXXXXXX");
            }
        }

        return errors;
    }
}
