package BookMyShow.coreEntity;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeOutInSeconds;
    private Date lockTime;
    private User lockedBy;

    // constructor
    public SeatLock(Seat seat, Show show, Integer timeOutInSeconds, Date date, User user) {
        this.seat = seat;
        this.show = show;
        this.timeOutInSeconds = timeOutInSeconds;
        this.lockTime = date;
        this.lockedBy = user;
    }

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeOutInSeconds);
        final Instant currentInstant = new Date().toInstant();

        return lockInstant.isBefore(currentInstant);
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public int getTimeoutInSeconds() {
        return timeOutInSeconds;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public User getLockedBy() {
        return lockedBy;
    }
}
