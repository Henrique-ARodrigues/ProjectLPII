package com.banking.account;

import com.banking.customer.CorporateCustomer;

public class CheckingAccount extends BankAccount {
    
    private double overdraftLimit;
    
    public CheckingAccount(double overdraftLimit, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        super(owner, balance, agency, accountNumber);
        this.overdraftLimit = overdraftLimit;
    }

    
     // Calcula as taxas da conta corrente.
      // Taxa fixa de manutenção de R$ 20,00.
      
    @Override
    public double calculateFees() {
        return 20.00;
    }

    // Getter
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Setter
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
