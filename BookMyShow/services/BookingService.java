package BookMyShow.services;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ConcurrentHashMap;


import BookMyShow.coreEntity.Booking;
import BookMyShow.coreEntity.Seat;
import BookMyShow.coreEntity.Show;
import BookMyShow.coreEntity.User;
import BookMyShow.interfaces.ISeatLockProvider;

public class BookingService {
    private final Map<String, Booking> showBookings;
    private final ISeatLockProvider seatLockProvider;

    public BookingService(ISeatLockProvider seatLockProvider) {
        this.showBookings = new ConcurrentHashMap<>();
        this.seatLockProvider = seatLockProvider;
    }

    public Booking createBooking(User user, Show show, List<Seat> seats) throws Exception {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new Exception("Some of the seats are already booked.");
        }

        seatLockProvider.lockSeats(show, seats, user);
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, seats, user);
        showBookings.put(bookingId, booking);
        return booking;
    }

    public List<Seat> getBookedSeats(Show show) {
        return showBookings.values().stream()
                .filter(booking -> booking.getShow().getId().equals(show.getId()))
                .filter(Booking::isConfirmed)
                .flatMap(booking -> booking.getSeatsBooked().stream())
                .collect(Collectors.toList());
    }

    public void confirmBooking(Booking booking, User user) throws Exception {
        if (!booking.getUser().getId().equals(user.getId())) {
            throw new Exception("Only the user who created the booking can confirm it.");
        }

        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new Exception("Only the user who locked the seat can confirm it.");
            }
        }

        booking.confirmBooking();
    }

    public boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        List<Seat> alreadyBookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (alreadyBookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }

    public Booking getBooking(String bookingId) {
        if (!showBookings.containsKey(bookingId)) {
            throw new IllegalArgumentException("Booking not found.");
        }
        return showBookings.get(bookingId);
    }
}
