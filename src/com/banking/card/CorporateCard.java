package com.banking.card;

public class CorporateCard {

    private String cardNumber;
    private String holderName;
    private String cvv;
    private String expirationDate;
    private String status;
    private double dailyLimit;
    private String holder;

    public CorporateCard(String cardNumber, String holderName, String cvv,
                         String expirationDate, String status, double dailyLimit,
                         String holder) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.status = status;
        this.dailyLimit = dailyLimit;
        this.holder = holder;
    }

    public void blockCard() {
        this.status = "BLOCKED";
        System.out.println("Cartão " + this.cardNumber + " foi BLOQUEADO.");
    }

    public void activateCard() {
        this.status = "ACTIVE";
        System.out.println("Cartão " + this.cardNumber + " foi ATIVADO.");
    }

    public void cancelCard() {
        this.status = "CANCELLED";
        System.out.println("Cartão " + this.cardNumber + " foi CANCELADO.");
    }

    public boolean canUseCard() {
        return "ACTIVE".equalsIgnoreCase(this.status);
    }

    // Getters
    public String getCardNumber() { return cardNumber; }
    public String getHolderName() { return holderName; }
    public String getCvv() { return cvv; }
    public String getExpirationDate() { return expirationDate; }
    public String getStatus() { return status; }
    public double getDailyLimit() { return dailyLimit; }
    public String getHolder() { return holder; }


    // Setters
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public void setStatus(String status) { this.status = status; }
    public void setDailyLimit(double dailyLimit) { this.dailyLimit = dailyLimit; }
    public void setHolder(String holder) { this.holder = holder; }
}