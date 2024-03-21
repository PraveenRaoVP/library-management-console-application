package com.librarymanagement.ManageBooks;

import com.librarymanagement.models.Library;
import com.librarymanagement.repository.BooksDatabase;
import com.librarymanagement.repository.LibraryBookDatabase;
import com.librarymanagement.repository.LibraryDatabase;
import com.librarymanagement.setup.LibrarySetupView;

import java.util.Scanner;

public class ManageBooksView {
    private final ManageBooksModel manageBooksModel;

    public ManageBooksView() {
        this.manageBooksModel = new ManageBooksModel(this);
    }

    public void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see the list of libraries? (y/n)");
        String choice = sc.nextLine();
        if(choice.equals("y")) {
            System.out.println("Library ID \t Library Name \t Phone No \t Email Id \t Address");
            for(Library lib: LibraryDatabase.getInstance().getLibraries()) {
                System.out.println(lib.getLibraryId() + "\t" + lib.getLibraryName() + "\t" + lib.getPhoneNo() + "\t" + lib.getEmailId() + "\t" + lib.getAddress());
            }
        }
        System.out.println("Enter the id of the library: ");
        int libraryId = sc.nextInt();
        System.out.println("Enter the number of books you wish to add: ");
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++) {
            System.out.println("Enter the book name: ");
            String name = sc.nextLine();
            System.out.println("Enter the author name: ");
            String author = sc.nextLine();
            System.out.println("Enter the publication: ");
            String publication = sc.nextLine();
            System.out.println("Enter the edition: ");
            String edition = sc.nextLine();
            System.out.println("Enter the journal: ");
            String journal = sc.nextLine();
            System.out.println("Enter the available count: ");
            int availableCount = sc.nextInt();
            System.out.println("Enter the volume: ");
            int volume = sc.nextInt();
            sc.nextLine();
            manageBooksModel.addBook(libraryId, name, author, publication, edition, journal, availableCount, volume);
            BooksDatabase.getInstance().pushDataToJSON();
            LibraryBookDatabase.getInstance().pushDataToJSON();
        }
    }

    public void removeBook() {
        BooksDatabase.getInstance().pullDataFromJSON();
        LibraryBookDatabase.getInstance().pullDataFromJSON();
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see the list of libraries? (y/n)");
        String choice = sc.nextLine();
        if(choice.equals("y")) {
            System.out.println("Library ID \t Library Name \t Phone No \t Email Id \t Address");
            for(Library lib: LibraryDatabase.getInstance().getLibraries()) {
                System.out.println(lib.getLibraryId() + "\t" + lib.getLibraryName() + "\t" + lib.getPhoneNo() + "\t" + lib.getEmailId() + "\t" + lib.getAddress());
            }
        }
        System.out.println("Enter the id of the library: ");
        int libraryId = sc.nextInt();
        sc.nextLine();
        System.out.println("Do you wish to see the list of books? (y/n)");
        choice = sc.nextLine();
        if(choice.equals("y")) {
            LibrarySetupView librarySetupView = new LibrarySetupView();
            librarySetupView.viewLibraryBooks(libraryId);
        }
        System.out.println("Enter the id of the book: ");
        int bookId = sc.nextInt();
        sc.nextLine();
        manageBooksModel.removeBook(libraryId, bookId);
        BooksDatabase.getInstance().pushDataToJSON();
        LibraryBookDatabase.getInstance().pushDataToJSON();
    }

    public void updateBookCount() {
        LibraryDatabase.getInstance().pullDataFromJSON();
        BooksDatabase.getInstance().pullDataFromJSON();
        LibraryBookDatabase.getInstance().pullDataFromJSON();
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see the list of libraries? (y/n)");
        String choice = sc.nextLine();
        if(choice.equals("y")) {
            System.out.println("Library ID \t Library Name \t Phone No \t Email Id \t Address");
            for(Library lib: LibraryDatabase.getInstance().getLibraries()) {
                System.out.println(lib.getLibraryId() + "\t" + lib.getLibraryName() + "\t" + lib.getPhoneNo() + "\t" + lib.getEmailId() + "\t" + lib.getAddress());
            }
        }
        System.out.println("Enter the id of the library: ");
        int libraryId = sc.nextInt();
        System.out.println("Enter the id of the book: ");
        int bookId = sc.nextInt();
        System.out.println("Enter the new count: ");
        int count = sc.nextInt();
        manageBooksModel.updateBookCount(libraryId, bookId, count);
        BooksDatabase.getInstance().pushDataToJSON();
        LibraryBookDatabase.getInstance().pushDataToJSON();
    }

    public void viewBooks() {
        LibraryDatabase.getInstance().pullDataFromJSON();
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see the list of libraries? (y/n)");
        String choice = sc.nextLine();
        if(choice.equals("y")) {
            System.out.println("Library ID \t Library Name \t Phone No \t Email Id \t Address");
            for(Library lib: LibraryDatabase.getInstance().getLibraries()) {
                System.out.println(lib.getLibraryId() + "\t" + lib.getLibraryName() + "\t" + lib.getPhoneNo() + "\t" + lib.getEmailId() + "\t" + lib.getAddress());
            }
        }
        System.out.println("Enter the id of the library: ");
        int libraryId = sc.nextInt();
        manageBooksModel.viewBooks(libraryId);
    }

    public void showAlert(String s) {
        System.out.println(s);
    }
}
