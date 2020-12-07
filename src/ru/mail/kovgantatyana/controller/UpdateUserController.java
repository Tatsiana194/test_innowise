package ru.mail.kovgantatyana.controller;

import ru.mail.kovgantatyana.servise.UserService;
import ru.mail.kovgantatyana.servise.impl.UserServiceImpl;
import ru.mail.kovgantatyana.servise.model.UserDTO;
import ru.mail.kovgantatyana.servise.tools.MyScanner;

import java.util.List;

public class UpdateUserController {
    private static UpdateUserController instanse;
    UserService userService = UserServiceImpl.getInstance();
    private MyScanner myScanner = MyScanner.getInstance();
    UserDTO userDTO = new UserDTO();

    private UpdateUserController() {
    }

    public static UpdateUserController getInstanse() {
        if (instanse == null) {
            instanse = new UpdateUserController();
        }
        return instanse;
    }

    public void updateUserByName() {
        System.out.println("enter the name of the user which you want to redact");
        userDTO = userService.getUserDTOByUserName(myScanner.getOneStringFromConsole());

        System.out.println("this is the user you want to change\n" +
                userDTO +
                "\n\nEnter new data" +
                "\nenter new name");
        userDTO.setName(myScanner.getOneStringFromConsole());
        System.out.println("enter new surname");
        userDTO.setSurname(myScanner.getOneStringFromConsole());
        System.out.println("enter new email");
        userDTO.setEmail(myScanner.getOneStringFromConsole());
        myScanner.getLineFromConsole();
        System.out.println("enter new roles");
        userDTO.setRoleList(myScanner.getLineFromConsole());
        System.out.println("enter new phones");
        userDTO.setPhoneList(myScanner.getLineFromConsole());
        List<String> errors = userService.userValidation(userDTO);
        if (!errors.isEmpty()) {
            System.out.println("\nyou have several errors while entering user\n" +
                    errors +
                    "\ntry again");
            return;
        }
        userService.updateUser(userDTO);
        System.out.println("user was changed");
    }
}
