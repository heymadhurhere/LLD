package ParkingLot;

import ParkingLot.enums.*;
import ParkingLot.utils.DateTimeParser;
import ParkingLot.Factory.*;
import ParkingLot.model.*;
import ParkingLot.service.*;
import ParkingLot.Strategy.payment.*;
import ParkingLot.Strategy.pricing.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();
        EntryGate entryGate = new EntryGate("EG1");
        ExitGate exitGate = new ExitGate("XG1");

        lot.setPricingStrategy(PricingStrategyFactory.get(PricingStrategyType.valueOf("EVENT_BASED")));

        ParkingFloor floor1 = new ParkingFloor("Floor1");
        floor1.addSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("F1S2", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("F1S3", VehicleType.TRUCK));
        floor1.addSpot(new ParkingSpot("F1S4", VehicleType.CAR));
        lot.addFloor(floor1);

        System.out.println("--------------------");

        Vehicle car = VehicleFactory.create("BR06AZ5414", VehicleType.CAR); 

        LocalDateTime entryTime = DateTimeParser.parse("8 May 1:13 PM 2026");
        Ticket ticket = entryGate.parkVehicle(car, entryTime);

        Vehicle bike = VehicleFactory.create("BR01SP1304", VehicleType.BIKE); 

        LocalDateTime inTime = DateTimeParser.parse("12 Apr 7:43 PM 2026");
        Ticket tkt = entryGate.parkVehicle(bike, inTime);

        System.out.println("--------------------");

        lot.printStatus();

        System.out.println("--------------------");

        LocalDateTime exitTime = DateTimeParser.parse("08 May 4:00 PM 2026");
        exitGate.unparkVehicle(ticket.getTicketId(), exitTime, PaymentMode.CARD);

        LocalDateTime outTime = DateTimeParser.parse("13 Apr 4:00 PM 2026");
        exitGate.unparkVehicle(tkt.getTicketId(), outTime, PaymentMode.UPI);

        System.out.println("--------------------");

        lot.printStatus();
    }
}