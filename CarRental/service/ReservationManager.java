package CarRental.service;

import java.util.*;

import CarRental.core.User;
import CarRental.Factory.Vehicle;
import CarRental.service.Reservation;


public class ReservationManager {
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private int nextReservationId;

    public ReservationManager() {
        this.reservations = new HashMap<>();
        this.nextReservationId = 1;
    }

    public Reservation createReservation(User user, Vehicle vehicle, Date startDate, Date endDate) {
        Reservation reservation = new Reservation(nextReservationId++, user, vehicle, null, null, startDate, endDate);
        reservations.put(reservation.getId(), reservation);
        user.addReservation(reservation);
        return reservation;
    }

    public void confirmReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.confirmReservation();
        }
    }

    public void startRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.startRental();
        }
    }

    public void completeRental(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.completeRental();
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation =  reservations.get(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
        }
    }

    public Reservation getReservation(int reservationId) {
        return reservations.get(reservationId);
    }
}
