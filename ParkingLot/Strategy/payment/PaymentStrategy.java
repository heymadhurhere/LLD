package ParkingLot.Strategy.payment;

import ParkingLot.model.Ticket;

public interface PaymentStrategy {
    boolean processPayment(Ticket ticket, double amount);
}
