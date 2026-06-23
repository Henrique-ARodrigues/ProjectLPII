package com.banking.transaction;

import java.time.LocalDateTime;
import com.banking.account.BankAccount;
import com.banking.exception.InvalidDataException;

public class PixTransaction extends Transaction {
    
    private String pixKey;

    // Construtor corrigido: ordem idêntica à classe mãe + atributo específico no final
    public PixTransaction(BankAccount source, double amount, LocalDateTime timestamp, BankAccount destination, String pixKey) {
        super(source, amount, timestamp, destination);
        this.pixKey = pixKey;
    }    

    @Override
    public boolean validate() {
        try {
            if (this.pixKey == null || this.pixKey.trim().isEmpty()) {
                this.setStatus("FAILED");
                // Lança a sua exceção customizada se a chave for inválida
                throw new InvalidDataException("Transaction FAILED: Invalid or empty Pix Key.");
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Se passar na validação do Pix, chama a validação antifraude
        return super.validate();
    }

    // Getter
    public String getPixKey() {
        return pixKey;
    }

    // Setter
    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }
}