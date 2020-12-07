package ru.mail.kovgantatyana.controller;

import ru.mail.kovgantatyana.servise.UserService;
import ru.mail.kovgantatyana.servise.impl.UserServiceImpl;
import ru.mail.kovgantatyana.servise.model.UserDTO;

import java.util.List;

public class ShowAllUsersController {
    private static ShowAllUsersController instanse;
    UserService userService = UserServiceImpl.getInstance();

    private ShowAllUsersController() {
    }

    public static ShowAllUsersController getInstanse() {
        if (instanse == null) {
            instanse = new ShowAllUsersController();
        }
        return instanse;
    }

    public void getAllUserDto() {
        List<UserDTO> userDTOList = userService.getUserDTOList();
        System.out.println("\nwe have next users:");
        for (UserDTO userDTO : userDTOList) {
            System.out.println(userDTO);
        }
    }

}
