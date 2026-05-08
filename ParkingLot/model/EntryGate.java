package ParkingLot.model;

import ParkingLot.enums.GateType;
import ParkingLot.service.ParkingLot;
import java.time.LocalDateTime;

public class EntryGate extends Gate {

    public EntryGate(String id) {
        super(id);
    }

    @Override
    public GateType getType() {
        return GateType.ENTRY;
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        return ParkingLot.getInstance().parkVehicle(vehicle, entryTime);
    }
}
