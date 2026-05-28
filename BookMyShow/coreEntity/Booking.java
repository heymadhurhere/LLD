package BookMyShow.coreEntity;

import java.util.List;

import BookMyShow.enums.BookingStatus;

public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final User user;
    private BookingStatus status;

    //constructor
    public Booking(String id, Show show, List<Seat> seatsBooked, User user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.status = BookingStatus.CONFIRMED;
    }

    public boolean isConfirmed() {
        return status == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() {
        if (this.status != BookingStatus.CREATED) {
            throw new IllegalStateException("Only confirm a already created booking.");
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public void expireBooking() {
        if (this.status != BookingStatus.CREATED) {
            throw new IllegalStateException("Only expire a already created booking.");
        }
        this.status = BookingStatus.EXPIRED;
    }

    public List<Seat> getSeatsBooked() {
        return this.seatsBooked;
    }

    public User getUser() {
        return this.user;
    }

    public Show getShow() {
        return this.show;
    }

    public String getId() {
        return this.id;
    }
}
