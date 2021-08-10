package com.mx.BusinessCardManagementSystem.bean;

public class RecycleBin {
    private String id;
    private String username;
    private String sex;
    private String userid;
    private String userpassword;
    private String email;

    public RecycleBin(){}

    public RecycleBin(String id, String username, String sex, String userid, String userpassword, String email) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.userid = userid;
        this.userpassword = userpassword;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RecycleBin{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", userid='" + userid + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
