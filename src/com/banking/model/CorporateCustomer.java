package com.banking.model;

public class CorporateCustomer extends User {
    
    private String cnpj;
    private String businessName;
    private String tradeName;
    
    public CorporateCustomer(String cnpj, String businessName, String tradeName, String email, String password) {
        super(email, password);
        this.cnpj = cnpj;
        this.businessName = businessName;
        this.tradeName = tradeName;
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