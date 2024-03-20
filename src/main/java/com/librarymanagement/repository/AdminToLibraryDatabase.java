package com.librarymanagement.repository;

import java.util.HashMap;
import java.util.Map;

public class AdminToLibraryDatabase {
    private final Map<Integer, Integer> libraryIdToAdminId = new HashMap<>();
    private AdminToLibraryDatabase() {
    }

    private static AdminToLibraryDatabase adminToLibraryDatabase;

    public static AdminToLibraryDatabase getInstance() {
        if(adminToLibraryDatabase == null) {
            adminToLibraryDatabase = new AdminToLibraryDatabase();
        }
        return adminToLibraryDatabase;
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
