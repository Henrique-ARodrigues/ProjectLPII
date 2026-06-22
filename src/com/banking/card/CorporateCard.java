package com.banking.card;

import com.banking.model.Employee;

public class CorporateCard {

    private String cardNumber;
    private String holderName;
    private String cvv;
    private String expirationDate;
    private String status;
    private double dailyLimit;
    private Employee holder;

    public CorporateCard(String cardNumber, String holderName, String cvv,
                         String expirationDate, String status, double dailyLimit,
                         Employee holder) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.status = status;
        this.dailyLimit = dailyLimit;
        this.holder = holder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public Employee getHolder() {
        return holder;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void setHolder(Employee holder) {
        this.holder = holder;
    }
}