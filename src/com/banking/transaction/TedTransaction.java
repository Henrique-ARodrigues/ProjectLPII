package com.banking.transaction;

import java.time.LocalDateTime;
import com.banking.account.BankAccount;
import com.banking.exception.InvalidDataException;

public class TedTransaction extends Transaction {

    private String targetBankCode;


    
    public TedTransaction(BankAccount source, double amount, LocalDateTime timestamp, BankAccount destination, String targetBankCode) {
        super(source, amount, timestamp, destination);
        this.targetBankCode = targetBankCode;
    }

    @Override
    public boolean validate() {
        try {
            if (this.targetBankCode == null || this.targetBankCode.trim().isEmpty()) {
                this.setStatus("FAILED");
                throw new InvalidDataException("Transaction FAILED: Invalid or empty Target Bank Code for TED.");
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Se o código do banco for válido, chama a checagem antifraude
        return super.validate();
    }

    // Getter
    public String getTargetBankCode() {
        return targetBankCode;
    }

    // Setter
    public void setTargetBankCode(String targetBankCode) {
        this.targetBankCode = targetBankCode;
    }
}