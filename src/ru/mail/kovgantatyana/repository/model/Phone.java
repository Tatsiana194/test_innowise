package ru.mail.kovgantatyana.repository.model;

public class Phone {
    private int id;
    private int userId;
    private String number;

    public Phone(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        number = builder.number;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getNumber() {
        return number;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private int userId;
        private String number;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public Builder number(String val) {
            number = val;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }
}
