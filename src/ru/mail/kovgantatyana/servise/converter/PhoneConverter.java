package ru.mail.kovgantatyana.servise.converter;

import ru.mail.kovgantatyana.repository.model.Phone;

public class PhoneConverter {

    public static Phone phoneDtoToPhone(String number, int userId) {
        Phone phone = new Phone.Builder()
                .userId(userId)
                .number(number)
                .build();
        return phone;
    }
}
