package ParkingLot.Factory;

import ParkingLot.enums.PaymentMode;
import ParkingLot.Strategy.payment.*;


public class PaymentStrategyFactory {
    public static PaymentStrategy get(PaymentMode mode) {
        return switch (mode) {
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
            case UPI -> new UPIpayment();
        };
    }

}
