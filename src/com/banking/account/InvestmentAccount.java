package com.banking.account;

import com.banking.model.CorporateCustomer;


public class InvestmentAccount extends BankAccount {
    
    private double totalInvested;
    
    public InvestmentAccount(double totalInvested, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        super(owner, balance, agency, accountNumber);
        this.totalInvested = totalInvested;
    }
    
    
    // Calcula as taxas da conta de investimento.
     // Taxa de administração de 0.5% sobre o total investido.
     
    @Override
    public double calculateFees() {
        // 0.5% de taxa administrativa sobre o valor investido
        return this.totalInvested * 0.005;
    }

    // Getters
    public double getTotalInvested() {
        return totalInvested;
    }
    
    // Setters
    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }
}
