package ru.mail.kovgantatyana.controller;

import ru.mail.kovgantatyana.servise.UserService;
import ru.mail.kovgantatyana.servise.impl.UserServiceImpl;
import ru.mail.kovgantatyana.servise.model.UserDTO;
import ru.mail.kovgantatyana.servise.tools.MyScanner;

import java.util.List;

public class SaveUserController {
    private static SaveUserController instanse;
    UserService userService = UserServiceImpl.getInstance();
    private MyScanner myScanner = MyScanner.getInstance();

    private SaveUserController() {
    }

    public static SaveUserController getInstanse() {
        if (instanse == null) {
            instanse = new SaveUserController();
        }
        return instanse;
    }

    public void saveUser() {
        UserDTO userDTO = new UserDTO();
        System.out.println("enter the name");
        userDTO.setName(myScanner.getOneStringFromConsole());
        System.out.println("enter the surname");
        userDTO.setSurname(myScanner.getOneStringFromConsole());
        System.out.println("enter the email");
        userDTO.setEmail(myScanner.getOneStringFromConsole());
        myScanner.getLineFromConsole();
        System.out.println("enter the roles");
        userDTO.setRoleList(myScanner.getLineFromConsole());
        System.out.println("enter the phones");
        userDTO.setPhoneList(myScanner.getLineFromConsole());
        List<String> errors = userService.userValidation(userDTO);
        if (!errors.isEmpty()) {
            System.out.println("you have several errors while entering user\n" +
                    errors +
                    "\ntry again");
            return;
        }
        userService.saveUser(userDTO);
        System.out.println("user was saved");
    }
}