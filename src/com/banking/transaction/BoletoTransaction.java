package com.banking.transaction;

import java.time.LocalDateTime;
import com.banking.account.BankAccount;
import com.banking.exception.InvalidDataException;

public class BoletoTransaction extends Transaction {
    
    private String barCode;


    
    public BoletoTransaction(BankAccount source, double amount, LocalDateTime timestamp, BankAccount destination, String barCode) {
        super(source, amount, timestamp, destination);
        this.barCode = barCode;
    }    

    @Override
    public boolean validate() {
        try {
            if (this.barCode == null || this.barCode.trim().isEmpty()) {
                this.setStatus("FAILED");
                throw new InvalidDataException("Transaction FAILED: Invalid or empty Barcode.");
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        // Se o boleto for válido, chama o antifraude
        return super.validate();
    }

    // Getter
    public String getBarCode() {
        return barCode;
    }

    // Setter
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}