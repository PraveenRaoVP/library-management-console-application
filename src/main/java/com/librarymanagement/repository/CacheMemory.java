package com.librarymanagement.repository;

public class CacheMemory {
    private static CacheMemory cacheMemory;

    private int currentAdminId;

    private CacheMemory(){}

    public static CacheMemory getInstance() {
        if (cacheMemory == null) {
            cacheMemory = new CacheMemory();
        }
        return cacheMemory;
    }

    public void pushAllDataToJSON() {
        AdminDatabase.getInstance().pushDataToJSON();
        AdminToLibraryDatabase.getInstance().pushDataToJSON();
        BooksDatabase.getInstance().pushDataToJSON();
        CredentialsDatabase.getInstance().pushDataToJSON();
        CustomerDatabase.getInstance().pushDataToJSON();
        CustomerBookDatabase.getInstance().pushDataToJSON();
        LibraryDatabase.getInstance().pushDataToJSON();
        LibraryBookDatabase.getInstance().pushDataToJSON();
    }

    public int getCurrentAdmin() {
        return currentAdminId;
    }

    public void setCurrentAdmin(int currentAdminId) {
        this.currentAdminId = currentAdminId;
    }

    public String getCurrentTimeAsString() {
        return java.time.LocalDateTime.now().toString();
    }

    public int getDaysBetweenDates(String issueDate, String returnDate) {
        java.time.LocalDateTime issue = java.time.LocalDateTime.parse(issueDate);
        java.time.LocalDateTime ret = java.time.LocalDateTime.parse(returnDate);
        return (int) java.time.temporal.ChronoUnit.DAYS.between(issue, ret);
    }
}
