package com.librarymanagement.repository;


import java.util.HashMap;
import java.util.Map;

public class CustomerBookDatabase {
    // customerId -> issueDate -> fine
    private final Map<Integer, Map<String, Double>> customerIdToIssueDateToFine = new HashMap<>();
    // customerId -> libraryId -> bookId
    private final Map<Integer, Map<Integer, Integer>> customerIdToLibraryIdtoBookId = new HashMap<>();
    private CustomerBookDatabase() {
    }

    private static CustomerBookDatabase customerBookDatabase;

    public static CustomerBookDatabase getInstance() {
        if(customerBookDatabase == null) {
            customerBookDatabase = new CustomerBookDatabase();
        }
        return customerBookDatabase;
    }

    public Map<Integer, Map<Integer, Integer>> getCustomerIdToLibraryIdtoBookId() {
        return customerIdToLibraryIdtoBookId;
    }

    public void addCustomerBook(int customerId, int libraryId, int bookId) {
        if(customerIdToLibraryIdtoBookId.containsKey(customerId)) {
            customerIdToLibraryIdtoBookId.get(customerId).put(libraryId, bookId);
        } else {
            Map<Integer, Integer> libraryIdToBookId = new HashMap<>();
            libraryIdToBookId.put(libraryId, bookId);
            customerIdToLibraryIdtoBookId.put(customerId, libraryIdToBookId);
        }
    }

    public void removeCustomerBook(int customerId, int libraryId, int bookId) {
        if(customerIdToLibraryIdtoBookId.containsKey(customerId)) {
            customerIdToLibraryIdtoBookId.get(customerId).remove(libraryId);
        }
    }

    public Map<Integer, Integer> getLibraryIdToBookId(int customerId) {
        return customerIdToLibraryIdtoBookId.get(customerId);
    }

    public Map<Integer, Map<String, Double>> getCustomerIdToIssueDateToFine() {
        return customerIdToIssueDateToFine;
    }

    public void addCustomerFine(int customerId, String issueDate, double fine) {
        if(customerIdToIssueDateToFine.containsKey(customerId)) {
            customerIdToIssueDateToFine.get(customerId).put(issueDate, fine);
        } else {
            Map<String, Double> issueDateToFine = new HashMap<>();
            issueDateToFine.put(issueDate, fine);
            customerIdToIssueDateToFine.put(customerId, issueDateToFine);
        }
    }

    public void removeCustomerFine(int customerId, String issueDate) {
        if(customerIdToIssueDateToFine.containsKey(customerId)) {
            customerIdToIssueDateToFine.get(customerId).remove(issueDate);
        }
    }

    // if the return date is more than 7 days from the issue date, the fine is 2rs per day
    public double calculateFine(String returnDate) {
        String[] returnDateArr = returnDate.split("-");
        int returnDay = Integer.parseInt(returnDateArr[0]);
        int returnMonth = Integer.parseInt(returnDateArr[1]);
        int returnYear = Integer.parseInt(returnDateArr[2]);
        String[] issueDateArr = returnDate.split("-");
        int issueDay = Integer.parseInt(issueDateArr[0]);
        int issueMonth = Integer.parseInt(issueDateArr[1]);
        int issueYear = Integer.parseInt(issueDateArr[2]);
        if(returnYear > issueYear) {
            return 2 * (returnDay - issueDay + 30 * (returnMonth - issueMonth) + 365 * (returnYear - issueYear));
        } else if(returnMonth > issueMonth) {
            return 2 * (returnDay - issueDay + 30 * (returnMonth - issueMonth));
        } else {
            return 2 * (returnDay - issueDay);
        }
    }

    public int returnLibraryId(int customerId) {
        return customerIdToLibraryIdtoBookId.get(customerId).keySet().iterator().next();
    }

    public void removeCustomerBooks(int customerId) {
        customerIdToLibraryIdtoBookId.remove(customerId);
        customerIdToIssueDateToFine.remove(customerId);
    }
}
