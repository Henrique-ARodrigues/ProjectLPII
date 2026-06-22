package com.banking.transaction;

import java.time.LocalDateTime;
import com.banking.account.BankAccount;

public class PixTransaction extends Transaction {
    
    private String pixKey;
    
    

    public PixTransaction(BankAccount source, double amount, LocalDateTime timestamp, String pixKey) {
        super(source, amount, timestamp);
        this.pixKey = pixKey;
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
    
