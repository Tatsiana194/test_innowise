package ru.mail.kovgantatyana.controller;

import ru.mail.kovgantatyana.servise.UserService;
import ru.mail.kovgantatyana.servise.impl.UserServiceImpl;
import ru.mail.kovgantatyana.servise.model.UserDTO;
import ru.mail.kovgantatyana.servise.tools.MyScanner;

public class ShowOneUserController {
    private static ShowOneUserController instanse;
    UserService userService = UserServiceImpl.getInstance();
    private MyScanner myScanner = MyScanner.getInstance();

    private ShowOneUserController() {
    }

    public static ShowOneUserController getInstanse() {
        if (instanse == null) {
            instanse = new ShowOneUserController();
        }
        return instanse;
    }

    public void getUserDto() {
        System.out.println("enter the name of the user which you want to see");
        String username = myScanner.getOneStringFromConsole();
        UserDTO userDTO = userService.getUserDTOByUserName(username);

        System.out.println(userDTO);
        System.out.println("this user has roles:");
        for (String s : userDTO.getRoleList()) {
            System.out.println(s);
        }
        System.out.println("this user has phones:");
        for (String s : userDTO.getPhoneList()) {
            System.out.println(s);
        }
    }
}
