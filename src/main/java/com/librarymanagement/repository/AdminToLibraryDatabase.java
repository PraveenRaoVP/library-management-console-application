package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminToLibraryDatabase {
    private final Map<Integer, Integer> libraryIdToAdminId = new HashMap<>();
    private AdminToLibraryDatabase() {
    }
    private String fileNamePath = "src/main/resources/adminToLibrary.json"; // Path to the file where the books are stored
    private static AdminToLibraryDatabase adminToLibraryDatabase;

    public static AdminToLibraryDatabase getInstance() {
        if(adminToLibraryDatabase == null) {
            adminToLibraryDatabase = new AdminToLibraryDatabase();
        }
        return adminToLibraryDatabase;
    }

    public void pushDataToJSON() {
        // Code to push the data to the file
        ObjectMapper mapper = new ObjectMapper();
        try{
            File file = new File(fileNamePath);
            mapper.writeValue(file, libraryIdToAdminId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            libraryIdToAdminId.clear();
            libraryIdToAdminId.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Integer>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> getLibraryIdToAdminId() {
        return libraryIdToAdminId;
    }

    public void addLibraryAdmin(int libraryId, int adminId) {
        libraryIdToAdminId.put(libraryId, adminId);
    }

    public int getAdminByLibraryId(int libraryId) {
        if(libraryIdToAdminId.containsKey(libraryId)) {
            return libraryIdToAdminId.get(libraryId);
        }
        return -1;
    }

    public void removeLibraryAdmin(int libraryId) {
        libraryIdToAdminId.remove(libraryId);
    }

    public void removeAdmin(int adminId) {
        for (Map.Entry<Integer, Integer> entry : libraryIdToAdminId.entrySet()) {
            if (entry.getValue() == adminId) {
                libraryIdToAdminId.remove(entry.getKey());
            }
        }
    }

}
