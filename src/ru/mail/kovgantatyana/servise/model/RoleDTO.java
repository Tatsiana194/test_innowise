package ru.mail.kovgantatyana.servise.model;

public class RoleDTO {
    private int id;
    private String role;

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "this role is "+ role;
    }
}
