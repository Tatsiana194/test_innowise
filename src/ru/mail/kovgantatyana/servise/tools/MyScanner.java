package ru.mail.kovgantatyana.servise.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyScanner {
    private static MyScanner instance;
    private Scanner scan = new Scanner(System.in);

    private MyScanner() {
    }

    public static MyScanner getInstance() {
        if (instance == null) {
            instance = new MyScanner();
        }
        return instance;
    }

    public List<String> getLineFromConsole() {
        String stringFromConsole = scan.nextLine();
        String[] stringsFromConsole = stringFromConsole.split(" ");
        List<String> stringList = new ArrayList<>();
        for (String oneString : stringsFromConsole) {
            stringList.add(oneString);
        }
        return stringList;
    }

    public String getOneStringFromConsole(){
        return scan.next();
    }

    public int getIntFromConsole() {
        return scan.nextInt();
    }
}
