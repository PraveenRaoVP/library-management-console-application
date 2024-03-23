package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminToCredentialsDatabase {
    // adminId -> credId
    private final Map<Integer, Integer> adminIdToCredId = new HashMap<>();
    private AdminToCredentialsDatabase() {
    }

    private String fileNamePath = "src/main/resources/adminToCredentials.json";
    private static AdminToCredentialsDatabase adminToCredentialsDatabase;

    public static AdminToCredentialsDatabase getInstance() {
        if(adminToCredentialsDatabase == null) {
            adminToCredentialsDatabase = new AdminToCredentialsDatabase();
        }
        return adminToCredentialsDatabase;
    }

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try{
            mapper.writeValue(file, adminIdToCredId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try{
            adminIdToCredId.clear();
            adminIdToCredId.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Integer>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> getAdminIdToCredId() {
        return adminIdToCredId;
    }

    public void addAdminCred(int adminId, int credId) {
        adminIdToCredId.put(adminId, credId);
    }

    public void removeAdminCred(int adminId) {
        adminIdToCredId.remove(adminId);
    }

    public int getCredIdForAdmin(int adminId) {
        return adminIdToCredId.get(adminId);
    }

    public void deleteAdminCred(int adminId) {
        adminIdToCredId.remove(adminId);
    }

    public int getAdminId(int credId) {
        for(Map.Entry<Integer, Integer> entry: adminIdToCredId.entrySet()) {
            if(entry.getValue() == credId) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
