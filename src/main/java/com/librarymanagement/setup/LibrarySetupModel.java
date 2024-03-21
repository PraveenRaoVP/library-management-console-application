package com.librarymanagement.setup;

import com.librarymanagement.models.Book;
import com.librarymanagement.models.Library;
import com.librarymanagement.repository.BooksDatabase;
import com.librarymanagement.repository.LibraryBookDatabase;
import com.librarymanagement.repository.LibraryDatabase;

import java.util.List;

class LibrarySetupModel {
    private final LibrarySetupView librarySetupView;

    public LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.librarySetupView = librarySetupView;
    }


    public void addLibrary(String libraryName, String phoneNo, String emailId, String address) {
        int libraryId = getLibraries().size()+1;
        Library library = new Library(libraryId, libraryName, phoneNo, emailId, address);
        LibraryDatabase.getInstance().insertLibrary(library);
        LibraryDatabase.getInstance().pushDataToJSON();

    }

    public List<Library> getLibraries() {
        return LibraryDatabase.getInstance().getLibraries();
    }

    public void removeLibrary(int libraryId) {
        LibraryDatabase.getInstance().removeLibrary(libraryId);
    }

    public void updateLibrary(int libraryId, String libraryName, String phoneNo, String emailId, String address) {
        if(!LibraryDatabase.getInstance().getLibraryById(libraryId)) {
            librarySetupView.showAlert("Library does not exist");
            return;
        }
        LibraryDatabase.getInstance().updateLibrary(libraryId, libraryName, phoneNo, emailId, address);
    }

    public void viewLibraryBooks(int libraryId) {
        LibraryDatabase.getInstance().pullDataFromJSON();
        LibraryBookDatabase.getInstance().pullDataFromJSON();
        BooksDatabase.getInstance().pullDataFromJSON();
        List<Integer> bookIds = LibraryBookDatabase.getInstance().getBookIdsForLibrary(libraryId);
        for(int bookId: bookIds) {
            Book book = BooksDatabase.getInstance().getBookById(bookId);
            librarySetupView.showAlert(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublication() + "\t" + book.getEdition() + "\t" + book.getJournal() + "\t" + LibraryBookDatabase.getInstance().getBookCount(libraryId, book.getId()) + "\t" + book.getVolume());
        }
    }

    public boolean checkIfLibrariesExists() {
        return LibraryDatabase.getInstance().checkIfLibrariesExist();
    }

}
