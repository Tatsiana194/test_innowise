package ru.mail.kovgantatyana.repository.model;

public class Role {
    private int id;
    private String role;

    public Role(Builder builder) {
        id = builder.id;
        role = builder.role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String role;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder role(String val) {
            role = val;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
