package com.banking.transaction;

import java.util.UUID;
import java.time.LocalDateTime;

import com.banking.account.BankAccount;
import com.banking.security.AntiFraudEngine;

public abstract class Transaction {

    private UUID transactionID;
    private BankAccount source;
    private BankAccount destination;
    private double amount;
    private LocalDateTime timestamp;
    private String status;

    public Transaction(BankAccount source, double amount, LocalDateTime timestamp, BankAccount destination) {

        this.transactionID = UUID.randomUUID();
        this.amount = amount;
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
        this.status = "PENDING";
    }

    public boolean validate() {

        AntiFraudEngine antiFraud = new AntiFraudEngine();

        String riskScore = antiFraud.checkRiskScore(this);

        if ("HIGH".equalsIgnoreCase(riskScore)) {
            this.status = "REJECTED";
            return false;
        }

        return true;
    }

    public void process() {
        // Impede reprocessar transações que já saíram do estado PENDING
        if (!"PENDING".equalsIgnoreCase(this.status)) {
            System.out.println("ERROR: Transaction cannot be processed. Current status is already: " + this.status);
            return;
        }

        if (this.validate()) {
            try {
                // Executa o saque na conta de origem
                this.source.withdraw(this.amount);

                // Se houver uma conta de destino configurada, realiza o depósito
                if (this.destination != null) {
                    this.destination.deposit(this.amount);
                }

                // Define o status final de sucesso
                this.status = "APPROVED";

                System.out.println(
                    "Transaction " + this.transactionID +
                    " processed successfully!"
                );

            } catch (Exception e) {
                // Se o saque falhar (ex: saldo insuficiente), muda o status para FAILED
                this.status = "FAILED";

                System.out.println(
                    "Transaction FAILED: " + e.getMessage()
                );
            }

        } else {

            this.status = "REJECTED";
            System.out.println(
                "Transaction FAILED: Blocked by AntiFraud Engine."
            );
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

    public BankAccount getDestination() {
        return destination;
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