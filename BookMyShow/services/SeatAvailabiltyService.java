package BookMyShow.services;

import java.util.*;

import BookMyShow.coreEntity.Show;
import BookMyShow.coreEntity.Seat;
import BookMyShow.interfaces.ISeatLockProvider;
import BookMyShow.services.BookingService;


public class SeatAvailabiltyService {
    private final BookingService bookingService;
    private final ISeatLockProvider seatLockProvider;

    public SeatAvailabiltyService(final BookingService bookingService, final ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeat(Show show) {
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unavailableSeats = getUnavailableSeats(show);
        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(Show show) {
        List<Seat> unavailableSeats = new ArrayList<>(); 
        List<Seat> lockedSeats = seatLockProvider.getLockedSeats(show);
        for (Seat seat : lockedSeats) {
            unavailableSeats.add(seat);
        }
        return unavailableSeats;
    }
}
