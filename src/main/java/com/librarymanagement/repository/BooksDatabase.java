package com.librarymanagement.repository;

import com.librarymanagement.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksDatabase {
    private BooksDatabase() {}

    public static BooksDatabase booksDatabase;

    public static BooksDatabase getInstance() {
        if(booksDatabase == null) {
            booksDatabase = new BooksDatabase();
        }
        return booksDatabase;
    }

    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int bookId) {
        for(Book book: books) {
            if(book.getId() == bookId) {
                books.remove(book);
                break;
            }
        }
    }

    public Book getBookById(int bookId) {
        for(Book book: books) {
            if(book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
