public class PixTransaction extends Transaction {
    
    private String pixKey;
    
    
    public PixTransaction(BankAccount source, double amount, LocalDateTime timestamp, String pixKey) {
        super(source, amount, timestamp);
        this.pixKey = pixKey;
    }    
    
}
    
