package com.banking.security;

import com.banking.transaction.Transaction;

public class AntiFraudEngine {
    
    public String checkRiskScore(Transaction transaction) {
        // Regra de segurança: Transações acima de R$ 8.000,00 são de alto risco
        if (transaction.getAmount() > 8000.0) {
            return "HIGH";
        }
        
        // Se for um valor normal, o risco é baixo
        return "LOW";
    }
}
