package ru.mail.kovgantatyana.repository.property;

import java.util.ResourceBundle;

public class ConnectionProperty {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("connection");

    public static final String URL = resourceBundle.getString("URL");
    public static final String USERNAME = resourceBundle.getString("USERNAME");
    public static final String PASSWORD = resourceBundle.getString("PASSWORD");
}
