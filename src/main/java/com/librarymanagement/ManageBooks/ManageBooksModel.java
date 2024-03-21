package com.librarymanagement.ManageBooks;

import com.librarymanagement.models.Book;
import com.librarymanagement.repository.BooksDatabase;
import com.librarymanagement.repository.LibraryBookDatabase;

public class ManageBooksModel {
    private final ManageBooksView manageBooksView;

    public ManageBooksModel(ManageBooksView manageBooksView) {
        this.manageBooksView = manageBooksView;
    }

    public void addBook(int libraryId, String name, String author, String publication, String edition, String journal, int availableCount, int volume) {
        // Add book to database
        int bookId = BooksDatabase.getInstance().getBooks().size()+1;
        Book book = new Book(name, bookId, author, publication, edition, journal, availableCount, volume);
        BooksDatabase.getInstance().addBook(book);
        LibraryBookDatabase.getInstance().addBookToLibrary(libraryId, bookId, availableCount);
    }

    public void removeBook(int libraryId, int bookId) {
        // Remove book from database
        BooksDatabase.getInstance().removeBook(bookId);
        LibraryBookDatabase.getInstance().removeBookFromLibrary(libraryId, bookId);
    }

    public void updateBookCount(int libraryId, int bookId, int count) {
        // Update book count in database
        LibraryBookDatabase.getInstance().updateBookCount(libraryId, bookId, count);
    }

    public void viewBooks(int libraryId) {
        BooksDatabase.getInstance().pullDataFromJSON();
        LibraryBookDatabase.getInstance().pullDataFromJSON();
        // View books in library
        System.out.println("Book Id \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume");
        for(Book book: BooksDatabase.getInstance().getBooks()) {

            int count = LibraryBookDatabase.getInstance().getBookCount(libraryId, book.getId());
            if(count != -1) {
                manageBooksView.showAlert(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublication() + "\t" + book.getEdition() + "\t" + book.getJournal() + "\t" + count + "\t" + book.getVolume());
            }
        }
    }

    public void viewAllBooks() {
        // View all books
        System.out.println("Book Id \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume");
        for(Book book: BooksDatabase.getInstance().getBooks()) {
            manageBooksView.showAlert(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublication() + "\t" + book.getEdition() + "\t" + book.getJournal() + "\t" + book.getAvailableCount() + "\t" + book.getVolume());
        }
    }


}
