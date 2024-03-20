package com.librarymanagement.repository;

import com.librarymanagement.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDatabase {
    private AdminDatabase() {}

    public static AdminDatabase adminDatabase;
    private final List<Admin> adminList = new ArrayList<>();

    public static AdminDatabase getInstance() {
        if(adminDatabase == null) {
            adminDatabase = new AdminDatabase();
        }
        return adminDatabase;
    }


    public void insertAdmin(Admin admin) {
        adminList.add(admin);
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public Admin getAdmin(int id) {
        for (Admin admin : adminList) {
            if (admin.getAdminId() == id) {
                return admin;
            }
        }
        return null;
    }

    public void deleteAdmin(int adminId) {
        for(Admin admin: adminList) {
            if(admin.getAdminId() == adminId) {
                adminList.remove(admin);
                break;
            }
        }
    }

    public void updateAdmin(int adminId, String name, String email, String address) {
        for(Admin admin: adminList) {
            if(admin.getAdminId() == adminId) {
                admin.setName(name);
                admin.setEmailId(email);
                admin.setAddress(address);
                break;
            }
        }
    }
}
