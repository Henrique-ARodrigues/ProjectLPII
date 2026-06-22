package com.banking.account;

import com.banking.exception.InvalidDataException;
import com.banking.exception.InsufficientBalanceException;
import com.banking.model.CorporateCustomer;


public abstract class BankAccount {
    
    private CorporateCustomer owner;
    private double balance;
    private String agency;
    private String accountNumber;
    
    public BankAccount(CorporateCustomer owner, double balance, String agency, String accountNumber) {
        
        this.owner = owner;
        this.balance = balance;
        this.agency = agency;
        this.accountNumber = accountNumber;
        
    }
    
    
    
    public void deposit(double amount) throws InvalidDataException {
        if (amount <= 0) {
            throw new InvalidDataException("O valor do deposito deve ser positivo.");
        }

        this.balance += amount;
        System.out.println("Deposito de R$" + amount + " realizado com sucesso.");
    }

    public void withdraw(double amount) throws InvalidDataException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidDataException("O valor do saque deve ser positivo.");
        }

        if (amount > this.balance) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar o saque.");
        }

        this.balance -= amount;
        System.out.println("Saque de R$" + amount + " realizado com sucesso.");
    }

        public void bankTransfer(BankAccount target, double amount)
        throws InvalidDataException, InsufficientBalanceException {

        if (target == null) {
            throw new InvalidDataException("A conta de destino nao pode ser nula.");
        }

        this.withdraw(amount);
        target.deposit(amount);

        System.out.println("Transferencia de R$" + amount + " realizada com sucesso.");
    }

    public abstract double calculateFees();

    // Getters
    
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