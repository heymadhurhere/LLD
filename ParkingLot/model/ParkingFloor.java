package ParkingLot.model;

import java.util.HashMap;
import java.util.Map;

import ParkingLot.enums.VehicleType;

public class ParkingFloor {
    private final String id;
    private final Map<String, ParkingSpot> spots = new HashMap<>();

    public ParkingFloor(String id) {
        this.id = id;
    }

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getId(), spot);
    }

    public String getId() {
        return id;
    }

    public Map<String, ParkingSpot> getSpots() {
        return spots;
    }

    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : spots.values()) {
            if (spot.getAllowedType() == vehicleType && spot.tryOccupy()) {
                return spot;
            }
        }
        return null;
    }
}
