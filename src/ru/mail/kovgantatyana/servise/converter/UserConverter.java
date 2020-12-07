package ru.mail.kovgantatyana.servise.converter;

import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.servise.model.UserDTO;

public class UserConverter {

    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public static User userDtoToUser(UserDTO userDTO) {
        User user = new User.Builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .build();
        return user;
    }
}
