public class InternatonalAccount extends BankAccount {
    private String currency;
    private double exchangeRate;
    
    
    public InternatonalAccount(String currency, double exchangeRate, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        
        super(owner, balance, agency, accountNumber);
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        
    }
    
    
    @Override
    public double calculateFees() {
        
    }
    
}