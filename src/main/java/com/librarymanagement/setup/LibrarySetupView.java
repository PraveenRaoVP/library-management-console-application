package com.librarymanagement.setup;

import com.librarymanagement.models.Library;

import java.util.List;
import java.util.Scanner;

public class LibrarySetupView {
    private final LibrarySetupModel librarySetupModel;
    public LibrarySetupView() {
        librarySetupModel = new LibrarySetupModel(this);
    }

    public void addLibrary() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Library Name: ");
        String libraryName = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNo = sc.nextLine();
        System.out.print("Enter Email Id: ");
        String emailId = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        librarySetupModel.addLibrary(libraryName, phoneNo, emailId, address);
    }

    public void removeLibrary() {
        Scanner sc = new Scanner(System.in);
        if(!librarySetupModel.checkIfLibrariesExists()) {
            System.out.println("No libraries to remove");
            return;
        }

        System.out.println("Do you wish to see library details (y/n): ");
        char ch = sc.next().charAt(0);
        if(ch == 'y') {
            viewLibraries();
        }
        System.out.print("Enter Library Id: ");
        int libraryId = sc.nextInt();
        librarySetupModel.removeLibrary(libraryId);
    }
    public void viewLibraries() {
        List<Library> libraries = librarySetupModel.getLibraries();
        System.out.println("Library Id\tLibrary Name\tPhone No\tEmail Id\tAddress");
        for(Library library: libraries) {
            System.out.println(library.getLibraryId()+"\t"+library.getLibraryName()+"\t"+library.getPhoneNo()+"\t"+library.getEmailId()+"\t"+library.getAddress());
        }
    }

    public void updateLibrary() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see library details (y/n): ");
        char ch = sc.next().charAt(0);
        if(ch == 'y') {
            viewLibraries();
        }
        System.out.print("Enter Library Id: ");
        int libraryId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Library Name: ");
        String libraryName = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNo = sc.nextLine();
        System.out.print("Enter Email Id: ");
        String emailId = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        librarySetupModel.updateLibrary(libraryId, libraryName, phoneNo, emailId, address);
    }

    public void viewLibraryBooks() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see library details (y/n): ");
        char ch = sc.next().charAt(0);
        if(ch == 'y') {
            viewLibraries();
        }
        System.out.print("Enter Library Id: ");
        int libraryId = sc.nextInt();
        System.out.println("Book Id \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume");
        librarySetupModel.viewLibraryBooks(libraryId);
    }
    public void viewLibraryBooks(int libraryId) {
        System.out.println("Book Id \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume");
        librarySetupModel.viewLibraryBooks(libraryId);
    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
