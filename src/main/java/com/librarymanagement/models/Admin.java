package com.librarymanagement.models;

public class Admin {
    private int adminId;
    private String name;
    private String emailId;
    private String address;

    public Admin(int adminId, String name, String emailId, String address) {
        this.adminId = adminId;
        this.name = name;
        this.emailId = emailId;
        this.address = address;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
