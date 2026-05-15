package CarRental.strategy;

public class CreditcardPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Creditcard payment of: " + amount);
    }
    
}
