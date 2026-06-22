package com.banking.account;

import com.banking.customer.CorporateCustomer;

public class InternationalAccount extends BankAccount {
    
    private String currency;
    private double exchangeRate; 
    
    public InternationalAccount(String currency, double exchangeRate, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        super(owner, balance, agency, accountNumber);
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }
    
    
    //Calcula as taxas da conta internacional.
     //Taxa de conversão/manutenção de 1% sobre o saldo da conta.
    
    @Override
    public double calculateFees() {
        // Pega o saldo usando o getBalance() da classe mãe e aplica 1% (0.01)
        return this.getBalance() * 0.01;
    }

    
    

    // Getters
    public double getBalanceInForeignCurrency() {
        return this.getBalance() / this.exchangeRate;
    }
    public String getCurrency() {
        return currency;
    }
    public double getExchangeRate() {
        return exchangeRate;
    }
    
    
    // Setters
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
