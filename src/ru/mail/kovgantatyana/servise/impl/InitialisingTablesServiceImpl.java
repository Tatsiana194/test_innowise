package ru.mail.kovgantatyana.servise.impl;

import ru.mail.kovgantatyana.repository.CreateTablesRepository;
import ru.mail.kovgantatyana.repository.InsertTablesRepository;
import ru.mail.kovgantatyana.repository.impl.CreateTablesRepositoryImpl;
import ru.mail.kovgantatyana.repository.impl.InsertTablesRepositoryImpl;
import ru.mail.kovgantatyana.repository.model.Phone;
import ru.mail.kovgantatyana.repository.model.Role;
import ru.mail.kovgantatyana.repository.model.User;
import ru.mail.kovgantatyana.repository.model.UserRole;
import ru.mail.kovgantatyana.servise.GetFromFile;
import ru.mail.kovgantatyana.servise.InitialisingTablesService;

import java.util.List;

public class InitialisingTablesServiceImpl implements InitialisingTablesService {
    private static InitialisingTablesServiceImpl instance;

    private CreateTablesRepository createTables = CreateTablesRepositoryImpl.getInstance();
    private InsertTablesRepository insertTables = InsertTablesRepositoryImpl.getInstance();
    private GetFromFile getFromFile = GetFromFileImpl.getInstance();

    private InitialisingTablesServiceImpl() {
    }

    public static InitialisingTablesServiceImpl getInstance() {
        if (instance == null) {
            instance = new InitialisingTablesServiceImpl();
        }
        return instance;
    }

    @Override
    public void CreateInsertUserTable() {
        createTables.createUserTable();
        List<User> userList = getFromFile.getUserFromFile();
        insertTables.insertUserTable(userList);
    }

    @Override
    public void CreateInsertPhoneTable() {
        createTables.createPhoneTable();
        List<Phone> fhoneList= getFromFile.getPhoneFromFile();
        insertTables.insertPhoneTable(fhoneList);
    }

    @Override
    public void CreateInsertRoleTable() {
        createTables.createRoleTable();
        List<Role> roleList = getFromFile.getRoleFromFile();
        insertTables.insertRoleTable(roleList);
    }

    @Override
    public void CreateInsertUserRoleTable() {
        createTables.createUserRoleTable();
        List<UserRole> userRoleList = getFromFile.getUserRoleFromFile();
        insertTables.insertUserRoleTable(userRoleList);
    }
}
