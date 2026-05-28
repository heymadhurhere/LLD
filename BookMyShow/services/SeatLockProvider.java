package BookMyShow.services;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import BookMyShow.coreEntity.Seat;
import BookMyShow.interfaces.ISeatLockProvider;
import BookMyShow.coreEntity.SeatLock;
import BookMyShow.coreEntity.Show;
import BookMyShow.coreEntity.User;

public class SeatLockProvider implements ISeatLockProvider {
    private final int lockTimeoutInSeconds;

    private final Map<Show, Map<Seat, SeatLock>> locks;

    public SeatLockProvider(int lockTimeoutInSeconds) {
        this.lockTimeoutInSeconds = lockTimeoutInSeconds;
        this.locks = new ConcurrentHashMap<>();
    }

    @Override
    public void lockSeats(Show show, List<Seat> seats, User user) throws Exception {
        Map<Seat, SeatLock> showSeatLocks = locks.computeIfAbsent(show, k -> new ConcurrentHashMap<>());

        synchronized (showSeatLocks) {
            for (Seat seat : seats) {
                if (showSeatLocks.containsKey(seat)) {
                    SeatLock existingLock = showSeatLocks.get(seat);
                    if (!existingLock.isLockExpired()) {
                        throw new Exception("Seat" + seat.getId() + " is already locked");
                    }
                }
            }

            Date now = new Date();
            for (Seat seat : seats) {
                SeatLock lock = new SeatLock(seat, show, lockTimeoutInSeconds, now, user);
                showSeatLocks.put(seat, lock);
            }
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, User user) {
        Map<Seat, SeatLock> showSeatLocks = locks.get(show);
        if (showSeatLocks == null)
            return;

        synchronized (showSeatLocks) {
            for (Seat seat : seats) {
                SeatLock lock = showSeatLocks.get(seat);
                if (lock != null && lock.getLockedBy().getId().equals(user.getId())) {
                    showSeatLocks.remove(seat);
                }
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, User user) {
        Map<Seat, SeatLock> showSeatLocks = locks.get(show);
        if (showSeatLocks == null)
            return false;

        synchronized (showSeatLocks) {
            SeatLock lock = showSeatLocks.get(seat);
            return lock != null &&
                    !lock.isLockExpired() &&
                    lock.getLockedBy().getId().equals(user.getId());
        }
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        Map<Seat, SeatLock> showSeatLocks = locks.get(show);
        if (showSeatLocks == null)
            return new ArrayList<>();

        synchronized (showSeatLocks) {
            return showSeatLocks.entrySet().stream()
                    .filter(entry -> !entry.getValue().isLockExpired())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }
}
