package ru.mail.kovgantatyana.servise.tools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MyReader {
    private static MyReader instance;
    private char[] massiveSimvolovFomFile = new char[500];

    private MyReader() {
    }

    public static MyReader getInstance() {
        if (instance == null) {
            instance = new MyReader();
        }
        return instance;
    }

    public String getMassiveSymbolFromFile(String filePath) {
        try (Reader fileReader = new FileReader("resources/" + filePath + ".txt")) {
            int a;
            while ((a = fileReader.read(massiveSimvolovFomFile)) != -1) {
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(massiveSimvolovFomFile);
    }
}
