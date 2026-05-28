package BookMyShow.interfaces;

public interface PaymentStrategy {
    boolean processPayment(double amount);
}