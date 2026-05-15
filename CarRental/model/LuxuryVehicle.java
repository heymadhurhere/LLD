package CarRental.model;

import CarRental.enums.VehicleType;
import CarRental.Factory.Vehicle;

public class LuxuryVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 2.5;
    private static final double PREMIUM_FEE = 200.0;
    public LuxuryVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalPrice(int days) {
        return getBaseRentalPrice() * days * RATE_MULTIPLIER + PREMIUM_FEE;
    }
}
