package ru.mail.kovgantatyana.controller;

import ru.mail.kovgantatyana.servise.tools.MyScanner;

public class RegulatoryController {
    private static RegulatoryController instanse;
    private MyScanner myScanner = MyScanner.getInstance();
    private ShowAllUsersController showAll = ShowAllUsersController.getInstanse();
    private ShowOneUserController showOne = ShowOneUserController.getInstanse();
    private UpdateUserController updateUser = UpdateUserController.getInstanse();
    private DeleteUserController deleteUser = DeleteUserController.getInstanse();
    private SaveUserController saveUser = SaveUserController.getInstanse();

    private RegulatoryController() {
    }

    public static RegulatoryController getInstanse() {
        if (instanse == null) {
            instanse = new RegulatoryController();
        }
        return instanse;
    }

    public void geMassage() {
        System.out.println("What do you want to do?\n" +
                "- if LOOK THROUGH all users, please write 1\n" +
                "- if SEE ONE user, please write 2\n" +
                "- if REDACT ONE user, please write 3\n" +
                "- if CREATE NEW user, please write 4\n" +
                "- if DELETE ONE user, please write 5\n" +
                "After write a letter please press ENTER");
        switch (myScanner.getIntFromConsole()) {
            case 1:
                showAll.getAllUserDto();
                break;
            case 2:
                showOne.getUserDto();
                break;
            case 3:
                updateUser.updateUserByName();
                break;
            case 4:
                saveUser.saveUser();
                break;
            case 5:
                deleteUser.deleteUserByName();
                break;
        }
    }
}
