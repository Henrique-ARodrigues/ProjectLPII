package com.banking.card;

public class VirtualCard extends CorporateCard {

    private boolean isSingleUse;

    public VirtualCard(String cardNumber, String holderName, String cvv,
                       String expirationDate, String status, double dailyLimit,
                       String holder, boolean isSingleUse) {
        super(cardNumber, holderName, cvv, expirationDate, status, dailyLimit, holder);
        this.isSingleUse = isSingleUse;
    }

    public boolean isSingleUse() { return isSingleUse; }
    public void setSingleUse(boolean singleUse) { isSingleUse = singleUse; }
}