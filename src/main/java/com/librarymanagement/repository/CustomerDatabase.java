package com.librarymanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.models.Customer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDatabase {
    private CustomerDatabase() {}

    public static CustomerDatabase customerDatabase;

    public static CustomerDatabase getInstance() {
        if(customerDatabase == null) {
            customerDatabase = new CustomerDatabase();
        }
        return customerDatabase;
    }

    private Map<Integer, Customer> customerMap = new HashMap<>();
    private String fileNamePath = "src/main/resources/customers.json";

    public List<Customer> getCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    public void setCustomers(List<Customer> customers) {
        for(Customer customer: customers) {
            customerMap.put(customer.getCustomerId(), customer);
        }
    }

    public void addCustomer(Customer customer) {
        int customerId = customerMap.size() + 1;
        customer.setCustomerId(customerId);
        customerMap.put(customerId, customer);
    }

    public void removeCustomer(int customerId) {
        customerMap.remove(customerId);
    }

    public Customer getCustomerById(int customerId) {
        return customerMap.get(customerId);
    }

    public Customer getCustomerByName(String customerName) {
        for(Customer customer: customerMap.values()) {
            if(customer.getCustomerName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    public void pushDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            mapper.writeValue(file, customerMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullDataFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileNamePath);
            customerMap.clear();
            customerMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Customer>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
