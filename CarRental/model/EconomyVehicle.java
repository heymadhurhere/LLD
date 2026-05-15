package CarRental.model;

import CarRental.enums.VehicleType;
import CarRental.Factory.Vehicle;

public class EconomyVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 1.0; // Rate multiplier for Economy vehicles

    public EconomyVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalPrice(int days) {
        return getBaseRentalPrice() * days * RATE_MULTIPLIER;
    }
}
