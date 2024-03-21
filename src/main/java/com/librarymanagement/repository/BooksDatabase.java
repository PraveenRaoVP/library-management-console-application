package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksDatabase {
    private BooksDatabase() {}

    public static BooksDatabase booksDatabase;

    public static BooksDatabase getInstance() {
        if(booksDatabase == null) {
            booksDatabase = new BooksDatabase();
        }
        return booksDatabase;
    }

    private Map<Integer, Book> books = new HashMap<>();
    private String fileNamePath = "src/main/resources/books.json"; // Path to the file where the books are stored

    public void pushDataToJSON() {
        // Code to push the data to the file
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            mapper.writeValue(file, books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        // Code to pull the data from the file
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            books.clear();
            books.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Book>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        return books.isEmpty() ? new ArrayList<>() : new ArrayList<>(books.values());
    }

    public void setBooks(List<Book> books) {
        for(Book book: books) {
            this.books.put(book.getId(), book);
        }
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(int bookId) {
        books.remove(bookId);
    }

    public Book getBookById(int bookId) {
        return books.get(bookId);
    }
}
