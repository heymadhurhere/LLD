package BookMyShow.concreteClasses;

import BookMyShow.interfaces.PaymentStrategy;

public class UPIstrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of: " + amount);
        return true;
    }
}