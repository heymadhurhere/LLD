package CarRental.model;

import CarRental.enums.VehicleType;
import CarRental.Factory.Vehicle;

public class BikeVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 0.5;

    public BikeVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalPrice(int days) {
        return getBaseRentalPrice() * days * RATE_MULTIPLIER;
    }

}
