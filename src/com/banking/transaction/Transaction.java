import java.util.UUID;
import java.time.LocalDateTime;


public abstract class Transaction {
    
    private UUID transactionID;
    private BankAccount source
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
    
    /**
     * MÉTODO PROCESS
     * Tenta validar a transação e, se aprovada, efetua o saque na conta de origem.
     */
    public void process() {
        // Só avança se passar na validação do antifraude
        if(this.validate()) {
            
            // Verifica se a conta de origem tem saldo suficiente
            if (this.source.getBalance() >= this.amount) {
                this.source.withdraw(this.amount);
                // Mantém o status como "APPROVED" pois o dinheiro saiu com sucesso
            } else {
                this.status = "FAILED";
                System.out.println("Transaction FAILED: Insufficient funds.");
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
    
    // Setters
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    
}
