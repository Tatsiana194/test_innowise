package ru.mail.kovgantatyana.controller;

import ru.mail.kovgantatyana.servise.UserService;
import ru.mail.kovgantatyana.servise.impl.UserServiceImpl;
import ru.mail.kovgantatyana.servise.model.UserDTO;
import ru.mail.kovgantatyana.servise.tools.MyScanner;

public class DeleteUserController {
    private static DeleteUserController instanse;
    UserService userService = UserServiceImpl.getInstance();
    private MyScanner myScanner = MyScanner.getInstance();

    private DeleteUserController() {
    }

    public static DeleteUserController getInstanse() {
        if (instanse == null) {
            instanse = new DeleteUserController();
        }
        return instanse;
    }

    public void deleteUserByName() {
        System.out.println("enter the name of the user which you want to delete");
        UserDTO userDTO = userService.getUserDTOByUserName(myScanner.getOneStringFromConsole());
        System.out.println("\ndo you really want to delete this user \n" + userDTO +
                "\n yes - press 1\n" +
                "no - press any number");
        if (myScanner.getIntFromConsole() == 1) {
            userService.deleteUser(userDTO.getId());
        }
        System.out.println("user was deleted");
    }
}
