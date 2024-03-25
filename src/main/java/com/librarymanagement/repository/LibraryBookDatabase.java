package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Book;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryBookDatabase {
    // store books Id to corresponding library id
    private final Map<Integer, Map<Integer, Integer>> libraryIdToBookIdToCount = new HashMap<>();
    private LibraryBookDatabase() {}
    private String fileNamePath = "src/main/resources/libraryBooks.json";
    private static LibraryBookDatabase libraryBookDatabase;

    public static LibraryBookDatabase getInstance() {
        if(libraryBookDatabase == null) {
            libraryBookDatabase = new LibraryBookDatabase();
        }
        return libraryBookDatabase;
    }

    public void addBookToLibrary(int libraryId, int bookId, int count) {
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            bookIdToCount.put(bookId, count);
        } else {
            Map<Integer, Integer> bookIdToCount = new HashMap<>();
            bookIdToCount.put(bookId, count);
            libraryIdToBookIdToCount.put(libraryId, bookIdToCount);
        }
    }

    public void removeBookFromLibrary(int libraryId, int bookId) {
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            bookIdToCount.remove(bookId);
        }
    }

    public void updateBookCount(int libraryId, int bookId, int count) {
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            bookIdToCount.put(bookId, count);
        }
    }

    public Map<Integer, Integer> getBooksCount(int libraryId) {
        return libraryIdToBookIdToCount.get(libraryId);
    }

    public int getBookCount(int libraryId, int bookId) {
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            return bookIdToCount.get(bookId);
        }
        return -1;
    }

    public boolean checkIfBooksExist(int libraryId) {
        return libraryIdToBookIdToCount.containsKey(libraryId);
    }

    public void viewLibraryBooks(int libraryId) {
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            System.out.println("Book Id\tBook Name\tCount");
            for(Map.Entry<Integer, Integer> entry: bookIdToCount.entrySet()) {
                System.out.println(entry.getKey()+"\t"+entry.getValue());
            }
        }
    }

    public List<Integer> getBookIdsForLibrary(int libraryId) {
        List<Integer> bookIds = new ArrayList<>();
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            for(Map.Entry<Integer, Integer> entry: bookIdToCount.entrySet()) {
                bookIds.add(entry.getKey());
            }
        }
        return bookIds;
    }

    public List<Book> getBooksForLibrary(int libraryId) {
        List<Book> books = new ArrayList<>();
        if(libraryIdToBookIdToCount.containsKey(libraryId)) {
            Map<Integer, Integer> bookIdToCount = libraryIdToBookIdToCount.get(libraryId);
            for(Map.Entry<Integer, Integer> entry: bookIdToCount.entrySet()) {
                books.add(BooksDatabase.getInstance().getBookById(entry.getKey()));
            }
        }
        return books;
    }

    public List<Book> getBooksForCustomer(int customerId) {
        List<Book> books = new ArrayList<>();
        Map<Integer, Integer> libraryToBookId = CustomerBookDatabase.getInstance().getLibraryIdToBookId(customerId);
        for(Map.Entry<Integer, Integer> entry: libraryToBookId.entrySet()) {
            books.add(BooksDatabase.getInstance().getBookById(entry.getValue()));
        }
        return books;
    }

    public void pushDataToJSON() {
        // Code to push the data to the file
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try{
            mapper.writeValue(file, libraryIdToBookIdToCount);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            libraryIdToBookIdToCount.clear();
            libraryIdToBookIdToCount.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Map<Integer, Integer>>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
