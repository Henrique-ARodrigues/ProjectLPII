package com.banking.transaction;

import java.time.LocalDateTime;
import com.banking.account.BankAccount;

public class BoletoTransaction extends Transaction {
    
    private String barCode;

    public BoletoTransaction(BankAccount source, double amount, LocalDateTime timestamp, BankAccount destination String barCode) {
        super(source, amount, timestamp, destination);
        this.barCode = barCode;
    }    


    // Sobrescreve o método validate para aplicar regra de boletos
    @Override
    public boolean validate() {
        // Regra específica: O código de barras não pode ser nulo ou vazio
        if (this.barCode == null || this.barCode.trim().isEmpty()) {
            this.setStatus("FAILED");
            System.out.println("Transaction FAILED: Invalid or empty Barcode.");
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