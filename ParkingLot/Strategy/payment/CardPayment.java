package ParkingLot.Strategy.payment;

import ParkingLot.model.Ticket;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid Rs. " + amount + "for the ticket" + ticket.getTicketId() + " using card.");
        return true;
    }
}