package com.banking.transaction;

import java.time.LocalDateTime;
import com.banking.account.BankAccount;
import com.banking.card.CorporateCard;
import com.banking.exception.InvalidDataException;
import com.banking.exception.CardStatusException;

public class CardTransaction extends Transaction {

    private CorporateCard cardUsed;

    // Construtor alinhado com a ordem da classe mãe e adicionando o cartão utilizado
    public CardTransaction(BankAccount source, double amount, LocalDateTime timestamp, BankAccount destination, CorporateCard cardUsed) {
        super(source, amount, timestamp, destination);
        this.cardUsed = cardUsed;
    }

    @Override
    public boolean validate() {
        try {
            // O objeto do cartão não pode ser nulo
            if (this.cardUsed == null) {
                this.setStatus("FAILED");
                throw new InvalidDataException("Transaction FAILED: No card associated with this transaction.");
            }
            
            // O cartão não pode estar bloqueado ou inativo
            if ("INACTIVE".equalsIgnoreCase(this.cardUsed.getStatus()) || "BLOCKED".equalsIgnoreCase(this.cardUsed.getStatus())) {
                this.setStatus("FAILED");
                throw new CardStatusException("Transaction FAILED: The corporate card is currently blocked or inactive.");
            }
            
            // Verificar se a transação ultrapassa o limite diário do cartão
            if (this.getAmount() > this.cardUsed.getDailyLimit()) {
                this.setStatus("FAILED");
                throw new CardStatusException("Transaction FAILED: Amount exceeds the card's daily limit.");
            }

        } catch (InvalidDataException | CardStatusException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Só valida se passar em todas as regras
        return super.validate();
    }

    // Getter
    public CorporateCard getCardUsed() {
        return cardUsed;
    }

    // Setter
    public void setCardUsed(CorporateCard cardUsed) {
        this.cardUsed = cardUsed;
    }
}