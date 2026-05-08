package ParkingLot.model;

import ParkingLot.enums.VehicleType;

public class Bike extends Vehicle {
    public Bike(String number) {
        super(number, VehicleType.BIKE);
    }
}
