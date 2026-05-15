package CarRental.service;

import java.util.*;

import CarRental.Factory.*;
import CarRental.core.RentalStore;
import CarRental.core.User;
import CarRental.strategy.PaymentProcessor;
import CarRental.strategy.*;

//This is the main class managing the entire rental service. It is implemented as a singleton and handles users, stores, reservations, and payments.
public class RentalSystem {
    private static RentalSystem instance;
    private List<RentalStore> stores;
    private ReservationManager reservationManager;
    private VehicleFactory vehicleFactory;
    private PaymentProcessor paymentProcessor;
    private Map<Integer, User> users;
    private int nextUserId;

    private RentalSystem() {
        this.stores = new ArrayList<>();
        this.vehicleFactory = new VehicleFactory();
        this.reservationManager = new ReservationManager();
        this.paymentProcessor = new PaymentProcessor();
        this.users = new HashMap<>();
        this.nextUserId = 1;
    }

    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    public void addStore(RentalStore store) {
        stores.add(store);
    }

    public RentalStore getStore(int storeId) {
        for (RentalStore store : stores) {
            if (store.getId() == storeId) {
                return store;
            }
        }
        return null;
    }

    public List<RentalStore> getStores() {
        return stores;
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public Reservation createReservation(int userId, String registrationNumber, int pickUpStoreId, int returnStoreId,
            Date startDate, Date endDate) {
        User user = users.get(userId);
        RentalStore pickUpStore = getStore(pickUpStoreId);
        RentalStore returnStore = getStore(returnStoreId);

        Vehicle vehicle = (pickUpStore != null) ? pickUpStore.getVehicle(registrationNumber) : null;

        if (user != null && pickUpStore != null && returnStore != null && vehicle != null) {
            return reservationManager.createReservation(user, vehicle, startDate, endDate);
        }
        return null;
    }

    public boolean processPayment(int reservationId, PaymentStrategy paymentStrategy) {
        Reservation reservation = reservationManager.getReservation(reservationId);
        if (reservation != null) {
            boolean result = paymentProcessor.processPayment(reservation.getTotalAmount(), paymentStrategy);
            if (result) {
                reservationManager.confirmReservation(reservationId);
                return true;
            }
        }
        return false;
    }

    public void startRental(int reservationId) {
        reservationManager.startRental(reservationId);
    }

    public void completeRental(int reservationId) {
        reservationManager.completeRental(reservationId);
    }

    public void cancelReservation(int reservationId) {
        reservationManager.cancelReservation(reservationId);
    }

    public void registerUser(User user) {
        int userId = user.getId();
        if (users.containsKey(userId)) {
            System.out.println("User with ID " + userId + " already exists. Please use a different ID.");
            return;
        }
        users.put(userId, user);
    }
}