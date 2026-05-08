package ParkingLot.Strategy.payment;

import ParkingLot.model.Ticket;

public class UPIpayment implements PaymentStrategy{
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid Rs. " + amount + "for the ticket" + ticket.getTicketId() + " using UPI.");
        return true;
    }
}
