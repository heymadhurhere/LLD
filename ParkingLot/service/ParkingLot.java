package ParkingLot.service;

import ParkingLot.enums.*;
import ParkingLot.Factory.*;
import ParkingLot.model.*;
import ParkingLot.Strategy.payment.*;
import ParkingLot.Strategy.pricing.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();

    private final Map<String, ParkingFloor> floors = new HashMap<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    private PricingStrategy pricingStrategy;

    private ParkingLot() {
        this.pricingStrategy = PricingStrategyFactory.get(PricingStrategyType.TIME_BASED);
    }

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    public Map<String, ParkingFloor> getFloors() {
        return floors;
    }

    public Map<String, Ticket> getActiveTickets() {
        return activeTickets;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void addFloor(ParkingFloor floor) {
        floors.put(floor.getId(), floor);
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        for (ParkingFloor floor : floors.values()) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getType());

            if (spot != null) {
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket(ticketId, entryTime, null, vehicle, floor.getId(), spot.getId(), PaymentStatus.PENDING);
                activeTickets.put(ticketId, ticket);
                System.out.println("Vehicle " + vehicle.getNumber() + " has been parked in spot " + spot.getId());
                return ticket;
            }
        }
        System.out.println("No available spot for vehicle " + vehicle.getNumber() + " in any floor.");
        return null;
    }

    public void unparkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Ticket not found for vehicle " + ticketId);
            return;
        }

        double fee = pricingStrategy.calculateFee(ticket.getVehicle().getType(), ticket.getEntryTime(), exitTime);
        
        PaymentStrategy strategy = PaymentStrategyFactory.get(paymentMode);
        PaymentProcessor processor = new PaymentProcessor(strategy);
        boolean paid = processor.pay(ticket, fee);

        if (!paid) {
            System.out.println("Vehicle cannot be allowed to exit due to payment failure.");
            return;
        }

        ParkingSpot spot = floors.get(ticket.getFloorId()).getSpots().get(ticket.getSpotId());
        spot.vacate();
        ticket.setExitTime(exitTime);

        activeTickets.remove(ticketId);
        System.out.println("Vehicle " + ticket.getVehicle().getNumber() + " has been unparked. \nFee charged is Rs. " + fee);
    }

    public void printStatus() {
        floors.forEach((floorId, floor) -> {
            System.out.println("Floor: " + floorId);
            floor.getSpots().values().forEach(spot -> {
                System.out.println(" Spot " + spot.getId() + " [" + spot.getAllowedType() + "] - " + (spot.isOccupied() ? "Occupied" : "Free"));
            });
        });
    }
}
