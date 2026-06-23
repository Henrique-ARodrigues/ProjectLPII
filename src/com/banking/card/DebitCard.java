package com.banking.card;

import com.banking.account.CheckingAccount;

public class DebitCard extends CorporateCard {

    private CheckingAccount linkedAccount;

    public DebitCard(String cardNumber, String holderName, String cvv,
                     String expirationDate, String status, double dailyLimit,
                     String holder, CheckingAccount linkedAccount) {
        super(cardNumber, holderName, cvv, expirationDate, status, dailyLimit, holder);
        this.linkedAccount = linkedAccount;
    }

    public CheckingAccount getLinkedAccount() { return linkedAccount; }
    public void setLinkedAccount(CheckingAccount linkedAccount) { this.linkedAccount = linkedAccount; }
}