package ru.mail.kovgantatyana.servise;

import ru.mail.kovgantatyana.servise.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserDTOByUserName(String userName);

    List<UserDTO> getUserDTOList();

    void saveUser(UserDTO userDTO);

    void deleteUser(int userId);

    List<String> userValidation(UserDTO userDTO);

    void updateUser(UserDTO userDTO);
}
