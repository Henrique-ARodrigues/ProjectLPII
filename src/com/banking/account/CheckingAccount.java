package com.banking.account;

import com.banking.model.CorporateCustomer;
import com.banking.exception.InvalidDataException;
import com.banking.exception.InsufficientBalanceException;

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

        // Permite sacar usando o Saldo Disponível + Limite do Cheque Especial
        if (amount > (this.getBalance() + this.overdraftLimit)) {
            throw new InsufficientBalanceException("Saldo e limite de cheque especial insuficientes.");
        }

        double saldoAtual = this.getBalance();
        
        if (amount <= saldoAtual) {
            super.withdraw(amount);
        } else {
            // Entrou no cheque especial
            double diferenca = amount - saldoAtual;
            super.withdraw(saldoAtual); // Zera o saldo nominal
            this.overdraftLimit -= diferenca; // Consome o limite do cheque especial
            System.out.println("Saque realizado utilizando R$" + diferenca + " do Cheque Especial. Limite restante: R$" + this.overdraftLimit);
        }
    }

    @Override
    public double calculateFees() {
        return 20.00;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}