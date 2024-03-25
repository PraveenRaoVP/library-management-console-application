package com.librarymanagement.CustomerManagement;

import com.librarymanagement.models.Book;
import com.librarymanagement.models.Customer;
import com.librarymanagement.repository.*;

class CustomerManagerModel {
    private final CustomerManagerView customerManagerView;

    public CustomerManagerModel(CustomerManagerView customerManagerView) {
        this.customerManagerView = customerManagerView;
    }

    public static void issueBook(int libraryId, String customerName, String customerPhoneNo, String customerEmailId, String customerAddress, int bookId) {
        int customerId = CustomerDatabase.getInstance().getCustomers().size() + 1;
        Customer customer = new Customer(customerId, customerName, customerAddress, customerEmailId, customerPhoneNo);
        CustomerDatabase.getInstance().addCustomer(customer);
        if(BooksDatabase.getInstance().getBookById(bookId).getAvailableCount() == 0) {
            System.out.println("Book not available!");
            return;
        }
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
        String issueDate = CustomerBookDatabase.getInstance().getCustomerIdToIssueDateToFine().get(customerId).keySet().stream().findFirst().get();
        String returnDate = CacheMemory.getInstance().getCurrentTimeAsString();
        double fine = calculateFine(customerId, issueDate, returnDate);
        if(fine > 0) {
            CustomerBookDatabase.getInstance().addCustomerFine(customerId, returnDate, fine);
            customerManagerView.showAlert("You have a pending fine of " + fine + " for the book you have issued. Please pay the fine to return the book.");
            return;
        }
        customerManagerView.showAlert("Book returned successfully!");
    }

    public double calculateFine(int customerId, String issueDate, String returnDate) {
        // for each day after 7 days, fine is 10
        int days = CacheMemory.getInstance().getDaysBetweenDates(issueDate, returnDate);
        if(days <= 7) {
            return 0;
        }
        return (days - 7) * 10;
    }

    public void viewIssuedBooks(int customerId) {
        BooksDatabase.getInstance().pullDataFromJSON();
        LibraryBookDatabase.getInstance().pullDataFromJSON();
        CustomerBookDatabase.getInstance().pullDataFromJSON();

        System.out.println("Library Id \t Book Id");

        for(int libraryId: CustomerBookDatabase.getInstance().getLibraryIdToBookId(customerId).keySet()) {
            int bookId = CustomerBookDatabase.getInstance().getLibraryIdToBookId(customerId).get(libraryId);
            Book issuedBook = BooksDatabase.getInstance().getBookById(bookId);
            customerManagerView.showAlert(libraryId + "\t" + bookId + "\t" + issuedBook.getName() + "\t" + issuedBook.getAuthor() + "\t" + issuedBook.getPublication() + "\t" + issuedBook.getEdition() + "\t" + issuedBook.getJournal() + "\t" + issuedBook.getAvailableCount() + "\t" + issuedBook.getVolume());
        }
    }

    public Customer viewCustomerDetails(int customerId) {
        CustomerDatabase.getInstance().pullDataFromJSON();
        Customer customer = CustomerDatabase.getInstance().getCustomerById(customerId);
        return customer;
    }


}
