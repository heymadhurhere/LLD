package CarRental.Factory;

import CarRental.enums.VehicleType;
import CarRental.model.*;

public class VehicleFactory {
    public static Vehicle createVehicle(String registrationNumber, String model, VehicleType type,
            double baseRentalPrice) {
        switch (type) {
            case ECONOMY:
                return new EconomyVehicle(registrationNumber, model, type, baseRentalPrice);
            case LUXURY:
                return new LuxuryVehicle(registrationNumber, model, type, baseRentalPrice);
            case SUV:
                return new SUVvehicle(registrationNumber, model, type, baseRentalPrice);
            case BIKE:
                return new EconomyVehicle(registrationNumber, model, type, baseRentalPrice);
            case AUTO:
                return new EconomyVehicle(registrationNumber, model, type, baseRentalPrice);
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
