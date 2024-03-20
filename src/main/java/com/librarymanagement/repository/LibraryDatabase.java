package com.librarymanagement.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Book;
import com.librarymanagement.models.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private List<Library> libraries = new ArrayList<>();
    private String fileNamePath = "./src/main/resources/libraries.json";
    private LibraryDatabase() {
    }

    // TODO: Implement the method to get the libraries from the JSON file
    public List<Library> getLibrariesFromJSON() {
        ObjectMapper om = new ObjectMapper();
        List<Library> libraries = new ArrayList<>();
        return libraries;
    }

    //TODO: Implement the method to write the libraries to the JSON file
    public void writeLibrariesToJSON(List<Library> libraries) {
        ObjectMapper om = new ObjectMapper();

    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public static LibraryDatabase getInstance() {
        if(libraryDatabase == null) {
            libraryDatabase = new LibraryDatabase();
        }
        return libraryDatabase;
    }

    public void insertLibrary(Library library) {
        libraries.add(library);
        System.out.println("Lib details added");
    }

    public boolean checkDuplicateLibrary(String libraryName) {
        for(Library library: libraries) {
            if(library.getLibraryName().equals(libraryName)) {
                return true;
            }
        }
        return false;
    }

    public void removeLibrary(int libraryId) {
        for(Library library: libraries) {
            if(library.getLibraryId() == libraryId) {
                libraries.remove(library);
                System.out.println("Lib details removed");
                return;
            }
        }
        System.out.println("Library not found");
    }

    public boolean checkIfLibrariesExist() {
        return !libraries.isEmpty();
    }

    public void updateLibrary(int libraryId, String libraryName, String phoneNo, String emailId, String address) {
        for(Library library: libraries) {
            if(library.getLibraryId() == libraryId) {
                library.setLibraryName(libraryName);
                library.setPhoneNo(phoneNo);
                library.setEmailId(emailId);
                library.setAddress(address);
                System.out.println("Lib details updated");
                return;
            }
        }
        System.out.println("Library not found");
    }

    public boolean getLibraryById(int libraryId) {
        for(Library library: libraries) {
            if(library.getLibraryId() == libraryId) {
                return true;
            }
        }
        return false;
    }
}
