package ParkingLot.Strategy.pricing;

import ParkingLot.enums.VehicleType;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeBasedPricing implements PricingStrategy {
    // different rates for peak hours and off-peak hours
    private static final LocalTime PEAK_START = LocalTime.of(8, 0);
    private static final LocalTime PEAK_END = LocalTime.of(17, 0);

    private boolean isPeakHour(LocalTime time) {
    return !time.isAfter(PEAK_END) && !time.isBefore(PEAK_START);
    }

    @Override
    public double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }

        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long)Math.ceil(durationMinutes / 60.0);

        int peakHours = 0;
        int offPeakHours = 0;

        LocalDateTime cursor = entryTime.truncatedTo(ChronoUnit.HOURS);
        // If entry time has minutes, consider first hour partially occupied, count as 1 hour anyway (already rounded)

        for (int i = 0; i < totalHours; i++) {
            LocalTime hourStart = cursor.toLocalTime();
            if (isPeakHour(hourStart)) {
                peakHours++;
            } else {
                offPeakHours++;
            }
            cursor = cursor.plusHours(1);
        }

        double peakRate = switch(type) {
            case CAR -> 30.0;
            case BIKE -> 15.0;
            case TRUCK -> 50.0;
        
        };

        double offPeakRate = switch(type) {
            case CAR -> 20.0;
            case BIKE -> 10.0;
            case TRUCK -> 30.0;
        };

        return peakHours * peakRate + offPeakHours * offPeakRate;
    }
}
