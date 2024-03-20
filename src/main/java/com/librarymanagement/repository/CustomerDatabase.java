package com.librarymanagement.repository;

import com.librarymanagement.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabase {
    private CustomerDatabase() {}

    public static CustomerDatabase customerDatabase;

    public static CustomerDatabase getInstance() {
        if(customerDatabase == null) {
            customerDatabase = new CustomerDatabase();
        }
        return customerDatabase;
    }

    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(int customerId) {
        for(Customer customer: customers) {
            if(customer.getCustomerId() == customerId) {
                customers.remove(customer);
                break;
            }
        }
    }

    public Customer getCustomerById(int customerId) {
        for(Customer customer: customers) {
            if(customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomerByName(String customerName) {
        for(Customer customer: customers) {
            if(customer.getCustomerName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }
}
