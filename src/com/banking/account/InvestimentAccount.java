public class InvestmentAccount extends BankAccount {
    private double totalInvestided;
    
    
    public InvestmentAccount(double totalInvestided, CorporateCustomer owner, double balance, String agency, String accountNumber) {
        
        super(owner, balance, agency, accountNumber);
        this.totalInvestided = totalInvestided;
        
    }
    
    
    @Override
    public double calculateFees() {
        
    }
    
}