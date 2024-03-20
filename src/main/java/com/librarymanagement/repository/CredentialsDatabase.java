package com.librarymanagement.repository;

import com.librarymanagement.models.Credentials;

import java.util.ArrayList;
import java.util.List;

public class CredentialsDatabase {
    private static CredentialsDatabase credentialsDatabase;
    private final List<Credentials> credentialsList;

    private CredentialsDatabase() {
        credentialsList = new ArrayList<>();
        credentialsList.add(new Credentials("zsgs","123"));
    }

    public static CredentialsDatabase getInstance() {
        if (credentialsDatabase == null) {
            credentialsDatabase = new CredentialsDatabase();
        }
        return credentialsDatabase;
    }

    public List<Credentials> getCredentialsList() {
        return credentialsList;
    }

    public void insertCredentials(Credentials credentials) {
        credentialsList.add(credentials);
    }

    public boolean validateCredentials(String emailId, String password) {
        for (Credentials credentials : credentialsList) {
            if (credentials.getUserName().equals(emailId) && credentials.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
