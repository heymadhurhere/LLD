package ParkingLot.model;

import java.util.concurrent.atomic.AtomicBoolean;

import ParkingLot.enums.VehicleType;

public class ParkingSpot {
    private final String id;
    private final VehicleType allowedType;

    private AtomicBoolean occupied = new AtomicBoolean(false);

    public ParkingSpot(String id, VehicleType allowedType) {
        this.id = id;
        this.allowedType = allowedType;
    }

    public String getId() {
        return id;
    }

    public VehicleType getAllowedType() {
        return allowedType;
    }

    public VehicleType getVehicleType() {
        return allowedType;
    }

    public AtomicBoolean getOccupied() {
        return occupied;
    }

    public void setOccupied(AtomicBoolean occupied) {
        this.occupied = occupied;
    }

    public boolean tryOccupy() {
        return occupied.compareAndSet(false, true); // this is to maintain concurrency such that only one vehicle get access to the parking spot at once and not more than one
    }

    public void vacate() {
        occupied.set(false);
    }

    public boolean isOccupied() {
        return occupied.get();
    }

    public boolean isAvailable() {
        return !occupied.get();
    }
}
