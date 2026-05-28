package BookMyShow.concreteClasses;

import BookMyShow.interfaces.PaymentStrategy;

public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing CreditCard payment of: " + amount);
        return true;
    }
}
