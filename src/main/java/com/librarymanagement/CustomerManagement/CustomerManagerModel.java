package com.librarymanagement.CustomerManagement;

import com.librarymanagement.ManageBooks.ManageBooksView;
import com.librarymanagement.models.Book;
import com.librarymanagement.models.Customer;
import com.librarymanagement.repository.BooksDatabase;
import com.librarymanagement.repository.CustomerBookDatabase;
import com.librarymanagement.repository.CustomerDatabase;
import com.librarymanagement.repository.LibraryBookDatabase;

import java.util.List;

class CustomerManagerModel {
    private final CustomerManagerView customerManagerView;

    public CustomerManagerModel(CustomerManagerView customerManagerView) {
        this.customerManagerView = customerManagerView;
    }

    public static void issueBook(int libraryId, String customerName, String customerPhoneNo, String customerEmailId, String customerAddress, int bookId) {
        int customerId = CustomerDatabase.getInstance().getCustomers().size() + 1;
        Customer customer = new Customer(customerId, customerName, customerAddress, customerEmailId, customerPhoneNo);
        CustomerDatabase.getInstance().addCustomer(customer);
        CustomerBookDatabase.getInstance().addCustomerBook(customerId, libraryId, bookId);
        BooksDatabase.getInstance().getBookById(bookId).setAvailableCount(BooksDatabase.getInstance().getBookById(bookId).getAvailableCount() - 1);
        LibraryBookDatabase.getInstance().updateBookCount(libraryId, bookId, LibraryBookDatabase.getInstance().getBookCount(libraryId, bookId) - 1);
        System.out.println("Book issued successfully!");
    }


    public void returnBook(int customerId, int libraryId, int bookId) {
//        if(calculateFine(customerId) > 0) {
//            customerManagerView.showAlert("You have a pending fine of " + calculateFine(customerId) + " for the book you have issued. Please pay the fine to return the book.");
//            return;
//        }

        CustomerBookDatabase.getInstance().removeCustomerBook(customerId, libraryId, bookId);
        BooksDatabase.getInstance().getBookById(bookId).setAvailableCount(BooksDatabase.getInstance().getBookById(bookId).getAvailableCount() + 1);
        LibraryBookDatabase.getInstance().updateBookCount(libraryId, bookId, LibraryBookDatabase.getInstance().getBookCount(libraryId, bookId) + 1);
        customerManagerView.showAlert("Book returned successfully!");
    }

    public double calculateFine(int customerId) {
        double fine = 0;
        for(String issueDate: CustomerBookDatabase.getInstance().getCustomerIdToIssueDateToFine().get(customerId).keySet()) {
            fine += CustomerBookDatabase.getInstance().getCustomerIdToIssueDateToFine().get(customerId).get(issueDate);
        }
        return fine;
    }

    public void viewIssuedBooks(int customerId) {
        System.out.println("Library Id \t Book Id");
        ManageBooksView manageBooksView = new ManageBooksView();
        for(int libraryId: CustomerBookDatabase.getInstance().getLibraryIdToBookId(customerId).keySet()) {
            int bookId = CustomerBookDatabase.getInstance().getLibraryIdToBookId(customerId).get(libraryId);
            Book issuedBook = BooksDatabase.getInstance().getBookById(bookId);
            customerManagerView.showAlert(libraryId + "\t" + bookId + "\t" + issuedBook.getName() + "\t" + issuedBook.getAuthor() + "\t" + issuedBook.getPublication() + "\t" + issuedBook.getEdition() + "\t" + issuedBook.getJournal() + "\t" + issuedBook.getAvailableCount() + "\t" + issuedBook.getVolume());
        }
    }

    public Customer viewCustomerDetails(int customerId) {
        Customer customer = CustomerDatabase.getInstance().getCustomerById(customerId);
        return customer;
    }


}
