package com.banking.account;


import com.banking.model.CorporateCustomer;


abstract class BankAccount {
    
    private CorporateCustomer owner;
    protected double balance;
    private String agency;
    private String accountNumber;
    
    public BankAccount(CorporateCustomer owner, double balance, String agency, String accountNumber) {
        
        this.owner = owner;
        this.balance = balance;
        this.agency = agency;
        this.accountNumber = accountNumber;
        
    }
    
    
    
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit of R$" + amount + " successful.");
        } else {
            System.out.println("ERROR: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Withdrawal amount must be positive.");
        } else if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else {
            System.out.println("ERROR: Insufficient funds.");
        }
    }
    
    public abstract double calculateFees();
    
    public void bankTransfer(BankAccount source, double amount) {
        
        if(this.balance >= amount) {
            withdraw(amount);
            source.deposit(amount);
            System.out.println("Transfer of R$" + amount + " successful.");
        } else {
            System.out.println("ERROR: Insufficient funds.");
        }
        
    }
    
    
    
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAgency() {
        return agency;
    }
    public double getBalance() {
        return balance;
    }
    public CorporateCustomer getOwner() {
        return owner;
    }
    
    // Setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }
    
}