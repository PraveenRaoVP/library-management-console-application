package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Book;
import com.librarymanagement.models.Library;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private Map<Integer, Library> libraryList = new HashMap<>();
    private String fileNamePath = "src/main/resources/libraries.json";
    private LibraryDatabase() {
    }

    // TODO: Implement the method to get the libraries from the JSON file
    public void pullDataFromJSON() {
        ObjectMapper om = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            libraryList.putAll(om.readValue(file, new TypeReference<Map<Integer, Library>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: Implement the method to write the libraries to the JSON file
    public void pushDataToJSON() {
        ObjectMapper om = new ObjectMapper();
        File file = new File(fileNamePath);
        try {

            om.writeValue(file, libraryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Library> getLibraries() {
        return libraryList.values().stream().toList();
    }

    public static LibraryDatabase getInstance() {
        if(libraryDatabase == null) {
            libraryDatabase = new LibraryDatabase();
        }
        return libraryDatabase;
    }

    public void insertLibrary(Library library) {
        libraryList.put(library.getLibraryId(), library);
    }

    public boolean checkDuplicateLibrary(String libraryName) {
        for(Library library: libraryList.values()) {
            if(library.getLibraryName().equals(libraryName)) {
                return true;
            }
        }
        return false;
    }

    public void removeLibrary(int libraryId) {
        libraryList.remove(libraryId);
    }

    public boolean checkIfLibrariesExist() {
        return libraryList.size() > 0;
    }

    public void updateLibrary(int libraryId, String libraryName, String phoneNo, String emailId, String address) {
        Library library = libraryList.get(libraryId);
        library.setLibraryName(libraryName);
        library.setPhoneNo(phoneNo);
        library.setEmailId(emailId);
        library.setAddress(address);
        libraryList.put(libraryId, library);
    }

    public boolean getLibraryById(int libraryId) {
        return libraryList.containsKey(libraryId);
    }


}
