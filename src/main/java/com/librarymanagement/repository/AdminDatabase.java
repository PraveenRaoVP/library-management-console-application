package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDatabase {
    private AdminDatabase() {}

    public static AdminDatabase adminDatabase;
    private final Map<Integer, Admin> adminList = new HashMap<>();
    private String fileNamePath = "src/main/resources/admins.json"; // Path to the file where the admins are stored

    public static AdminDatabase getInstance() {
        if(adminDatabase == null) {
            adminDatabase = new AdminDatabase();
        }
        return adminDatabase;
    }

    public void pushDataToJSON() {
        // Code to push the data to the file
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try{
            mapper.writeValue(file, adminList);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        // Code to pull the data from the file
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            adminList.clear();
            adminList.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Admin>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAdmin(Admin admin) {
        adminList.put(admin.getAdminId(), admin);
    }

    public List<Admin> getAdminList() {
        return new ArrayList<>(adminList.values());
    }

    public Admin getAdmin(int id) {
        return adminList.get(id);
    }

    public void deleteAdmin(int adminId) {
        adminList.remove(adminId);
    }

    public void updateAdmin(int adminId, String name, String email, String address) {
        Admin admin = adminList.get(adminId);
        admin.setName(name);
        admin.setEmailId(email);
        admin.setAddress(address);
    }

    public int getAdminId(String username) {
        for(Admin admin: adminList.values()) {
            if(admin.getEmailId().equals(username)) {
                return admin.getAdminId();
            }
        }
        return -1;
    }
}
