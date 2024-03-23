package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Credentials;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredentialsDatabase {
    private static CredentialsDatabase credentialsDatabase;
    private final Map<Integer, Credentials> credentialsList = new HashMap<>();
    private String fileNamePath = "src/main/resources/credentials.json";
    private CredentialsDatabase() {

    }

    public static CredentialsDatabase getInstance() {
        if (credentialsDatabase == null) {
            credentialsDatabase = new CredentialsDatabase();
        }
        return credentialsDatabase;
    }

    public List<Credentials> getCredentialsList() {
        return new ArrayList<>(credentialsList.values());
    }

    public void insertCredentials(Credentials credentials) {
        int credentialsId = credentialsList.size() + 1;
        credentialsList.put(credentialsId, credentials);
    }

    public boolean validateCredentials(String emailId, String password) {
        for (Credentials credentials : credentialsList.values()) {
            if (credentials.getUserName().equals(emailId) && credentials.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try{
            mapper.writeValue(file, credentialsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try{
                credentialsList.clear();
                credentialsList.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Credentials>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkPassword(int adminId, String oldPassword) {
        AdminToCredentialsDatabase.getInstance().pullDataFromJSON();
        int credId = AdminToCredentialsDatabase.getInstance().getCredIdForAdmin(adminId);
        return credentialsList.get(credId).getPassword().equals(oldPassword);
    }

    public void updatePassword(int adminId, String newPassword) {
        credentialsList.get(adminId).setPassword(newPassword);
        pushDataToJSON();
    }

    public int getCredentialsId(String username) {
        for (Credentials credentials : credentialsList.values()) {
            if (credentials.getUserName().equals(username)) {
                return credentials.getCredId();
            }
        }
        return -1;
    }
}
