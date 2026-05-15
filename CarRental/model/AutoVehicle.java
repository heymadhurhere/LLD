package CarRental.model;

import CarRental.enums.VehicleType;
import CarRental.Factory.Vehicle;

public class AutoVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 1.2;

    public AutoVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    public double calculateRentalPrice(int days) {
        return getBaseRentalPrice() * days * RATE_MULTIPLIER;
    }
}
