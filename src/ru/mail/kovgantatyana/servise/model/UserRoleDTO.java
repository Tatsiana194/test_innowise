package ru.mail.kovgantatyana.servise.model;

public class UserRoleDTO {
    private int userId;
    private int roleId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
