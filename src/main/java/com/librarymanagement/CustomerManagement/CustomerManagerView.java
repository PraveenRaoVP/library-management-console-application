package com.librarymanagement.CustomerManagement;

import com.librarymanagement.models.Book;
import com.librarymanagement.models.Customer;
import com.librarymanagement.models.Library;
import com.librarymanagement.repository.*;
import com.librarymanagement.validation.ValidationModel;

import java.util.Scanner;

public class CustomerManagerView {
    private final CustomerManagerModel customerManagerModel;

    public CustomerManagerView() {
        this.customerManagerModel = new CustomerManagerModel(this);
    }


    public void issueBook() {
        LibraryDatabase.getInstance().pullDataFromJSON();
        BooksDatabase.getInstance().pullDataFromJSON();
        LibraryBookDatabase.getInstance().pullDataFromJSON();
        Scanner sc = new Scanner(System.in);
        int libraryId;
        if(CacheMemory.getInstance().getCurrentAdmin()==-1) {
            System.out.println("Do you wish to see the details of the libraries? (y/n)");
            String choice = sc.nextLine();
            if(choice.equals("y")) {
                System.out.println("Library ID \t Library Name \t Phone No \t Email Id \t Address");
                for(Library lib: LibraryDatabase.getInstance().getLibraries()) {
                    System.out.println(lib.getLibraryId() + "\t" + lib.getLibraryName() + "\t" + lib.getPhoneNo() + "\t" + lib.getEmailId() + "\t" + lib.getAddress());
                }
            }

            System.out.println("Enter the id of the library: ");
            libraryId = sc.nextInt();
            sc.nextLine();
        } else {
            libraryId = AdminToLibraryDatabase.getInstance().getLibraryId(CacheMemory.getInstance().getCurrentAdmin());
        }
        System.out.println("Enter the details of the customer: ");
        System.out.println("Enter the customer name: ");
        String customerName = sc.nextLine();
        String customerPhoneNo;
        while(true) {
            System.out.println("Enter the customer phone number: ");
            customerPhoneNo = sc.nextLine();

            if(!ValidationModel.getInstance().validatePhoneNo(customerPhoneNo)) {
                System.out.println("Invalid phone number! Enter the phone number again!");
            } else break;
        }
        String customerEmailId;
        while(true) {
            System.out.println("Enter the customer email id: ");
            customerEmailId = sc.nextLine();
            if(!ValidationModel.getInstance().validateEmail(customerEmailId)) {
                System.out.println("Invalid email id! Enter the email id again!");
            } else break;
        }
        System.out.println("Enter the customer address: ");
        String customerAddress = sc.nextLine();

        System.out.println("Do you wish to see the list of books in the library? (y/n)");
        String choice = sc.nextLine();
        if(choice.equalsIgnoreCase("y")) {
            System.out.println("Book ID \t Book Name \t Author \t Publication \t Edition \t Journal \t Available Count \t Volume");
            for(Book book: LibraryBookDatabase.getInstance().getBooksForLibrary(libraryId)) {
                System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublication() + "\t" + book.getEdition() + "\t" + book.getJournal() + "\t" + book.getAvailableCount() + "\t" + book.getVolume());
            }
        }
        System.out.println("Enter the book id: ");
        int bookId = sc.nextInt();
        sc.nextLine();
        CustomerManagerModel.issueBook(libraryId, customerName, customerPhoneNo, customerEmailId, customerAddress, bookId);
        CustomerDatabase.getInstance().pushDataToJSON();
        CustomerBookDatabase.getInstance().pushDataToJSON();
    }

    public void returnBook() {
        LibraryDatabase.getInstance().pullDataFromJSON();
        CustomerDatabase.getInstance().pullDataFromJSON();
        CustomerBookDatabase.getInstance().pullDataFromJSON();
        System.out.println("Enter the customer id: ");
        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();
        int libraryId = CustomerBookDatabase.getInstance().returnLibraryId(customerId);
        if(libraryId == -1) {
            System.out.println("The customer has not borrowed any books!");
            return;
        }
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
        CustomerDatabase.getInstance().pushDataToJSON();
        CustomerBookDatabase.getInstance().pushDataToJSON();
    }

    // TODO: check for fines in phase 2
//    public void checkForFine() {
//    }

    public void removeCustomer() {
        CustomerDatabase.getInstance().pullDataFromJSON();
        CustomerBookDatabase.getInstance().pullDataFromJSON();
        System.out.println("Enter the customer id: ");
        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();
        CustomerDatabase.getInstance().removeCustomer(customerId);
        CustomerBookDatabase.getInstance().removeCustomerBooks(customerId);
        CustomerDatabase.getInstance().pushDataToJSON();
        CustomerBookDatabase.getInstance().pushDataToJSON();
    }

    public void viewCustomer() {
        CustomerDatabase.getInstance().pullDataFromJSON();
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
