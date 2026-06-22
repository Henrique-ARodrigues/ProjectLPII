package com.banking.model;

import java.util.ArrayList;
import java.util.List;


public class CorporateCustomer extends User {
    
    private String cnpj;
    private String businessName;
    private String tradeName;
    private List<Employee> allowedEmployees;
    
    
    public CorporateCustomer(String cnpj, String businessName, String tradeName, String email, String password) {
        super(email, password);
        this.cnpj = cnpj;
        this.businessName = businessName;
        this.tradeName = tradeName;
        this.allowedEmployees = new ArrayList<>(); // Inicializa a lista vazia
    }
    
    // Método para autorizar um novo funcionário na conta da empresa
    public void addAllowedEmployee(Employee employee) {
        this.allowedEmployees.add(employee);
    }
    
    
    // Getters
    public String getCnpj() {
        return cnpj;
    }
    public String getBusinessName() {
        return businessName;
    }
    public String getTradeName() {
        return tradeName;
    }
    public List<Employee> getAllowedEmployees() {
        return allowedEmployees;
    }
    
    
    // Setters
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
    
}


