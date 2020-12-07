package ru.mail.kovgantatyana.repository;

import ru.mail.kovgantatyana.repository.model.Phone;

import java.util.List;

public interface PhoneRepository {
    List<Phone> getPhoneListByUser(int userId);

    void savePhones(List<Phone> phones);
}
