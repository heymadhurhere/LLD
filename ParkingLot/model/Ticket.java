package ParkingLot.model;

import ParkingLot.enums.PaymentStatus;
import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Vehicle vehicle;
    private String floorId;
    private String spotId;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public Ticket() {
    }

    public Ticket(String ticketId,
                  LocalDateTime entryTime,
                  LocalDateTime exitTime,
                  Vehicle vehicle,
                  String floorId,
                  String spotId,
                  PaymentStatus paymentStatus) {
        this.ticketId = ticketId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.vehicle = vehicle;
        this.floorId = floorId;
        this.spotId = spotId;
        this.paymentStatus = paymentStatus;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
