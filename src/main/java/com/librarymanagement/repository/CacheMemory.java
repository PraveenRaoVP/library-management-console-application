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

    public int getCurrentAdmin() {
        return currentAdminId;
    }

    public void setCurrentAdmin(int currentAdminId) {
        this.currentAdminId = currentAdminId;
    }
}
