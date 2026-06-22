package com.banking.service;

import java.util.ArrayList;
import java.util.List;

import com.banking.model.CorporateCustomer;

public class CorporateCustomerService {

    private List<CorporateCustomer> customers;

    public CorporateCustomerService() {
        customers = new ArrayList<>();
    }

    public CorporateCustomerService(List<CorporateCustomer> customers) {
        this.customers = customers;
    }

    public void create(CorporateCustomer customer) {
        customers.add(customer);
    }

    public List<CorporateCustomer> list() {
        return customers;
    }

    public CorporateCustomer findByCnpj(String cnpj) {
        for (CorporateCustomer customer : customers) {
            if (customer.getCnpj().equals(cnpj)) {
                return customer;
            }
        }

        return null;
    }

    public boolean update(String cnpj, String newBusinessName, String newTradeName) {
        CorporateCustomer customer = findByCnpj(cnpj);

        if (customer == null) {
            return false;
        }

        customer.setBusinessName(newBusinessName);
        customer.setTradeName(newTradeName);

        return true;
    }

    public boolean delete(String cnpj) {
        return customers.removeIf(customer ->
            customer.getCnpj().equals(cnpj)
        );
    }
}