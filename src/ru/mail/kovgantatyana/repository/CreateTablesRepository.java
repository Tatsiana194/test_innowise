package ru.mail.kovgantatyana.repository;

public interface CreateTablesRepository {

    void createUserTable();

    void createRoleTable();

    void createPhoneTable();

    void  createUserRoleTable();
}
