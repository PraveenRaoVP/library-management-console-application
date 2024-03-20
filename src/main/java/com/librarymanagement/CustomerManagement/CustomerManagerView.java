package com.librarymanagement.CustomerManagement;

import com.librarymanagement.models.Book;
import com.librarymanagement.models.Customer;
import com.librarymanagement.models.Library;
import com.librarymanagement.repository.*;

import java.util.Scanner;

public class CustomerManagerView {
    private final CustomerManagerModel customerManagerModel;

    public CustomerManagerView() {
        this.customerManagerModel = new CustomerManagerModel(this);
    }


    public void issueBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to see the details of the libraries? (y/n)");
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
        System.out.println("Enter the details of the customer: ");
        System.out.println("Enter the customer name: ");
        String customerName = sc.nextLine();
        System.out.println("Enter the customer phone number: ");
        String customerPhoneNo = sc.nextLine();
        System.out.println("Enter the customer email id: ");
        String customerEmailId = sc.nextLine();
        System.out.println("Enter the customer address: ");
        String customerAddress = sc.nextLine();

        System.out.println("Do you wish to see the list of books in the library? (y/n)");
        choice = sc.nextLine();
        if(choice.equals("y")) {
            System.out.println("Book ID \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume");
            for(Book book: LibraryBookDatabase.getInstance().getBooksForLibrary(libraryId)) {
                System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublication() + "\t" + book.getEdition() + "\t" + book.getJournal() + "\t" + book.getAvailableCount() + "\t" + book.getVolume());
            }
        }
        System.out.println("Enter the book id: ");
        int bookId = sc.nextInt();
        sc.nextLine();
        CustomerManagerModel.issueBook(libraryId, customerName, customerPhoneNo, customerEmailId, customerAddress, bookId);
    }

    public void returnBook() {
        System.out.println("Enter the customer id: ");
        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();
        int libraryId = CustomerBookDatabase.getInstance().returnLibraryId(customerId);
        if(LibraryBookDatabase.getInstance().getBooksForCustomer(customerId).isEmpty()) {
            System.out.println("The customer has not borrowed any books!");
            return;
        }
        System.out.println("The customer has borrowed the following books:-");
        System.out.println("Book ID \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume \t Library ID");
        for(Book book: LibraryBookDatabase.getInstance().getBooksForCustomer(customerId)) {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublication() + "\t" + book.getEdition() + "\t" + book.getJournal() + "\t" + book.getAvailableCount() + "\t" + book.getVolume() + "\t" + libraryId);
        }
        System.out.println("Enter the book id you wish to return: ");
        int bookId = sc.nextInt();
        customerManagerModel.returnBook(customerId, libraryId, bookId);
    }

    // TODO: check for fines in phase 2
//    public void checkForFine() {
//    }

    public void removeCustomer() {
        System.out.println("Enter the customer id: ");
        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();
        CustomerDatabase.getInstance().removeCustomer(customerId);
        CustomerBookDatabase.getInstance().removeCustomerBooks(customerId);
    }

    public void viewCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the customer id: ");
        int customerId = sc.nextInt();
        Customer customer = customerManagerModel.viewCustomerDetails(customerId);
        System.out.println("Customer Id: " + customer.getCustomerId());
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Customer Address: " + customer.getCustomerAddress());
        System.out.println("Customer Email Id: " + customer.getCustomerEmail());
        System.out.println("Customer Phone No: " + customer.getCustomerPhone());
        customerManagerModel.viewIssuedBooks(customerId);
    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
