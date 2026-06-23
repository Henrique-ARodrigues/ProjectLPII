package com.banking.card;

public class CreditCard extends CorporateCard {

    private double creditLimit;
    private double currentInvoice;

    public CreditCard(String cardNumber, String holderName, String cvv,
                      String expirationDate, String status, double dailyLimit,
                      String holder, double creditLimit, double currentInvoice) {
        super(cardNumber, holderName, cvv, expirationDate, status, dailyLimit, holder);
        this.creditLimit = creditLimit;
        this.currentInvoice = currentInvoice;
    }

    public double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(double creditLimit) { this.creditLimit = creditLimit; }
    public double getCurrentInvoice() { return currentInvoice; }
    public void setCurrentInvoice(double currentInvoice) { this.currentInvoice = currentInvoice; }
}