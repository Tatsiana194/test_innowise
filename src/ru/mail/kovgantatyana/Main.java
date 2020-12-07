package ru.mail.kovgantatyana;

import ru.mail.kovgantatyana.controller.RegulatoryController;
import ru.mail.kovgantatyana.servise.InitialisingTablesService;
import ru.mail.kovgantatyana.servise.impl.InitialisingTablesServiceImpl;
import ru.mail.kovgantatyana.servise.tools.MyScanner;

public class Main {
    private static InitialisingTablesService initialisingTablesService = InitialisingTablesServiceImpl.getInstance();
    private static RegulatoryController massage = RegulatoryController.getInstanse();
    private static MyScanner myScanner = MyScanner.getInstance();

    public static void createAndInsertDatabase() {
        initialisingTablesService.CreateInsertUserTable();
        initialisingTablesService.CreateInsertPhoneTable();
        initialisingTablesService.CreateInsertRoleTable();
        initialisingTablesService.CreateInsertUserRoleTable();
    }

    public static void main(String[] args) {
        createAndInsertDatabase();
        System.out.println("Hello");
        do {
            massage.geMassage();
            System.out.println("\nif you want to stop the application, press 1\n" +
                    "if you want to continue using the applications, press any number");
        } while (myScanner.getIntFromConsole() != 1);
    }
}
