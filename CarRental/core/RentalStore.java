package CarRental.core;

import CarRental.core.Location;
import CarRental.Factory.Vehicle;
import CarRental.enums.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

public class RentalStore {
    private int id;
    private String name;
    private Location location;

    private Map<String, Vehicle> vehicles; // Map of vehicle registration numbers to vehicles

    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    // keep track of vehicle in the map
    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate) {
        List<Vehicle> availableVehicle = new ArrayList<>();
        for (Vehicle vehicle : vehicles.values()) {
            if (vehicle.getStatus() == VehicleStatus.AVAILABLE) {
                availableVehicle.add(vehicle);
            }
        }
        return availableVehicle;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getRegistrationNumber(), vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle.getRegistrationNumber());
    }

    public boolean isVehicleAvailabe(String registrationNumber, Date startSate, Date endDate) {
        Vehicle vehicle = vehicles.get(registrationNumber);

        return vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE;
    }

    public Vehicle getVehicle(String registrationNumber) {
        return vehicles.get(registrationNumber);
    }

    public Map<String, Vehicle> getAllVehicles() {
        return vehicles;
    }

    public int getId() {
        return id;
    }
}
