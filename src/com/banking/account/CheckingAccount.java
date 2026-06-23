package com.banking.account;

import com.banking.model.CorporateCustomer;


public class CheckingAccount extends BankAccount {
    
    private double overdraftLimit;
    
    public CheckingAccount(double overdraftLimit, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        super(owner, balance, agency, accountNumber);
        this.overdraftLimit = overdraftLimit;
    }



    @Override
    public void withdraw(double amount) throws InvalidDataException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidDataException("O valor do saque deve ser positivo.");
        }

        // Permite sacar usando o Saldo + Limite do Cheque Especial
        if (amount > (this.getBalance() + this.overdraftLimit)) {
            throw new InsufficientBalanceException("Saldo e limite insuficientes para realizar o saque.");
        }

        
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
