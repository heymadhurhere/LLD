package CarRental.service;

import CarRental.core.User;
import CarRental.Factory.Vehicle;
import CarRental.core.RentalStore;
import CarRental.enums.ReservationStatus;
import CarRental.enums.VehicleStatus;

import java.util.Date;

public class Reservation {
    private int id;
    private User user;
    private Vehicle vehicle;
    private RentalStore pickUpStore;
    private RentalStore returnStore;
    private Date startDate;
    private Date endDate;
    private ReservationStatus status;
    private double totalAmount;

    public Reservation(int id, User user, Vehicle vehicle, RentalStore pickUpStore, RentalStore returnStore, Date startDate, Date endDate) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.pickUpStore = pickUpStore;
        this.returnStore = returnStore;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;

        // days between start and end date
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        this.totalAmount = vehicle.calculateRentalPrice(days);
    }

    public void confirmReservation() {
        if (status == ReservationStatus.PENDING) {
            status = ReservationStatus.CONFIRMED;
            vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }

    public void startRental() {
        if (status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.IN_PROGRESS;
            vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental() {
        if (status == ReservationStatus.IN_PROGRESS) {
            status = ReservationStatus.COMPLETED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void cancelReservation() {
        if (status == ReservationStatus.PENDING || status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.CANCELLED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public int getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
