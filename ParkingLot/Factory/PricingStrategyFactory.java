package ParkingLot.Factory;

import ParkingLot.enums.PricingStrategyType;
import ParkingLot.Strategy.pricing.*;

public class PricingStrategyFactory {
    public static PricingStrategy get(PricingStrategyType type) {
        return switch (type) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED -> new EventBasedPricing();
        };
    }
}
