public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    
    
    public CheckingAccount(double overdraftLimit, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        
        super(owner, balance, agency, accountNumber);
        this.overdraftLimit = overdraftLimit;
        
    }
    
    
    @Override
    public double calculateFees() {
        
    }
    
}