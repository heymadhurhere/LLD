package CarRental.Factory;

import CarRental.enums.VehicleStatus;
import CarRental.enums.VehicleType;

public abstract class Vehicle {
    private String registrationNumber;
    private String make;
    private String model;
    private int year;
    private VehicleType type;
    private VehicleStatus status;
    private double baseRentalPrice;

    //contructor
    public Vehicle (String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.type = type;
        this.baseRentalPrice = baseRentalPrice;
        this.status = VehicleStatus.AVAILABLE; // Default status when a vehicle is created
    }

    public abstract double calculateRentalPrice(int days); // it will be calculated in other class

    // getters and setters

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public double getBaseRentalPrice() {
        return baseRentalPrice;
    }
}
