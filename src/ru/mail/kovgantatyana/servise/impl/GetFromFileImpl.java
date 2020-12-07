package ru.mail.kovgantatyana.servise.impl;

import ru.mail.kovgantatyana.repository.model.Phone;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.repository.model.UserRole;
import ru.mail.kovgantatyana.servise.GetFromFile;
import ru.mail.kovgantatyana.servise.tools.MyReader;

import java.util.ArrayList;
import java.util.List;

public class GetFromFileImpl implements GetFromFile {
    private static GetFromFileImpl instance;
    MyReader reader = MyReader.getInstance();

    private GetFromFileImpl() {
    }

    public static GetFromFileImpl getInstance() {
        if (instance == null) {
            instance = new GetFromFileImpl();
        }
        return instance;
    }

    @Override
    public List<Phone> getPhoneFromFile() {
        List<Phone> phoneListFromFile = new ArrayList<>();
        String[] allFromFile = reader.getMassiveSymbolFromFile("fhone").split("\n");
        for (String oneStringFromFile : allFromFile) {
            int phoneId = 0;
            int userId = 0;
            String number = new String();
            String[] oneObject = oneStringFromFile.split(", ");
            for (String oneObjectParameter : oneObject) {
                String[] keyValue = oneObjectParameter.split("=");
                switch (keyValue[0]) {
                    case "fhoneid":
                        phoneId = Integer.parseInt(keyValue[1]);
                        break;
                    case "userid":
                        userId = Integer.parseInt(keyValue[1]);
                        break;
                    case "fhone":
                        number = keyValue[1];
                        break;
                }

            }
            if (phoneId == 0) {
                return phoneListFromFile;
            }
            Phone fhone = new Phone.Builder()
                    .id(phoneId)
                    .userId(userId)
                    .number(number)
                    .build();
            phoneListFromFile.add(fhone);
        }
        return phoneListFromFile;
    }

    @Override
    public List<User> getUserFromFile() {
        List<User> userListFromFile = new ArrayList<>();
        String[] allFromFile = reader.getMassiveSymbolFromFile("user").split("\n");
        for (String oneStringFromFile : allFromFile) {
            int userId = 0;
            String userName = new String();
            String surname = new String();
            String email = new String();
            String[] oneObject = oneStringFromFile.split(", ");
            for (String oneObjectParameter : oneObject) {
                String[] keyValue = oneObjectParameter.split("=");
                switch (keyValue[0]) {
                    case "id":
                        userId = Integer.parseInt(keyValue[1]);
                        break;
                    case "name":
                        userName = keyValue[1];
                        break;
                    case "surname":
                        surname = keyValue[1];
                        break;
                    case "email":
                        email = keyValue[1];
                        break;
                }
            }
            if (userId == 0) {
                return userListFromFile;
            }
            User user = new User.Builder()
                    .id(userId)
                    .name(userName)
                    .surname(surname)
                    .email(email)
                    .build();


            userListFromFile.add(user);
        }
        return userListFromFile;
    }

    @Override
    public List<Role> getRoleFromFile() {
        List<Role> roleListFromFile = new ArrayList<>();
        String[] allFromFile = reader.getMassiveSymbolFromFile("role").split("\n");
        for (String oneStringFromFile : allFromFile) {
            int id = 0;
            String roleName = new String();
            String[] oneObject = oneStringFromFile.split(", ");
            for (String oneObjectParameter : oneObject) {
                String[] keyValue = oneObjectParameter.split("=");
                switch (keyValue[0]) {
                    case "id":
                        id = Integer.parseInt(keyValue[1]);
                        break;
                    case "name":
                        roleName = keyValue[1];
                        break;
                }
            }
            if (id == 0) {
                return roleListFromFile;
            }
            Role role = new Role.Builder()
                    .id(id)
                    .role(roleName)
                    .build();
            roleListFromFile.add(role);
        }
        return roleListFromFile;
    }

    @Override
    public List<UserRole> getUserRoleFromFile() {
        List<UserRole> userRoleDTOListFromFile = new ArrayList<>();
        String[] allFromFile = reader.getMassiveSymbolFromFile("userrole").split(";");
        for (String oneStringFromFile : allFromFile) {
            UserRole userRole = new UserRole();
            String[] oneObject = oneStringFromFile.split(", ");
            for (String oneObjectParameter : oneObject) {
                String[] keyValueString = oneObjectParameter.split("=");
                switch (keyValueString[0]) {
                    case "userid":
                        userRole.setUserId(Integer.parseInt(keyValueString[1]));
                        break;
                    case "roleid":
                        userRole.setRoleId(Integer.parseInt(keyValueString[1]));
                        break;
                }
            }
            if (userRole.getUserId() == 0 | userRole.getRoleId() == 0) {
                return userRoleDTOListFromFile;
            }
            userRoleDTOListFromFile.add(userRole);
        }
        return userRoleDTOListFromFile;
    }
}
