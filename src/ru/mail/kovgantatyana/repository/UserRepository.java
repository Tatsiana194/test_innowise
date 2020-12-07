package ru.mail.kovgantatyana.repository;

import ru.mail.kovgantatyana.repository.model.User;

import java.util.List;

public interface UserRepository {

    User getUserByUserName(String username);

    List<User> getUserList();

    int saveUser(User user);

    void deleteUser(int userId);

    void updateUser(User user, int userId);
}
