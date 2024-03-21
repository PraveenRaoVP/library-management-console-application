package com.librarymanagement.models;

public class Credentials {
    private int credId;
    private String userName;
    private String password;

    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Credentials(){}

    public int getCredId() {
        return credId;
    }

    public void setCredId(int credId) {
        this.credId = credId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
