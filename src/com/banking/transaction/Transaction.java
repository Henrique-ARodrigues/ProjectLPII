package com.banking.transaction;

import java.util.UUID;
import java.time.LocalDateTime;
import com.banking.account.BankAccount;
import com.banking.security.AntiFraudEngine;

public abstract class Transaction {
    
    private UUID transactionID;
    private BankAccount source;
    private double amount;
    private LocalDateTime timestamp;
    private String status;
    
    
    
    public Transaction(BankAccount source, double amount, LocalDateTime timestamp) {
        this.transactionID = UUID.randomUUID();
        this.amount = amount;
        this.source = source;
        this.timestamp = timestamp;
        this.status = "PENDING";
    }
    
    
    
    public boolean validate() {
        AntiFraudEngine antiFraud = new AntiFraudEngine();
        
        String riskScore = antiFraud.checkRiskScore(this);
        
        if("HIGH".equalsIgnoreCase(riskScore)) {
            this.status = "REJECTED";
            return false;
        }
        
        this.status = "APPROVED";
        return true;
    }
    
    // Tenta validar a transação e, se aprovada, efetua o saque na conta de origem.
     
    public void process() {
    if (this.validate()) {
        try {
            this.source.withdraw(this.amount);
            this.status = "APPROVED";
            System.out.println("Transaction APPROVED.");
        } catch (Exception e) {
            this.status = "FAILED";
            System.out.println("Transaction FAILED: " + e.getMessage());
        }
    } else {
        System.out.println("Transaction FAILED: Blocked by AntiFraud Engine.");
    }
}
    

    
    
    // Getters
    public String getStatus() {
        return status; 
    }
    public double getAmount() {
        return amount;
    }
    public BankAccount getSource() { 
        return source; 
    }
    public UUID getTransactionId() {
        return transactionID;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    
    // Setters
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    
}
