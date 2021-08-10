package com.mx.BusinessCardManagementSystem.bean;

public class User {
    private String userId;
    private String password;
    private String identity;
    private String name;

    public User() {
    }

    public User(String userId, String password, String identity, String name) {
        this.userId = userId;
        this.password = password;
        this.identity = identity;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
