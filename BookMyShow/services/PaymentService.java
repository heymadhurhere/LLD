package BookMyShow.services;

import BookMyShow.interfaces.PaymentStrategy;
import BookMyShow.coreEntity.Booking;
import BookMyShow.coreEntity.User;


public class PaymentService {
    private final BookingService bookingService;
    private final PaymentStrategy paymentStrategy;

    public PaymentService(BookingService bookingService, PaymentStrategy paymentStrategy) {
        this.bookingService = bookingService;
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(String bookingId, User user, double amount) throws Exception {
        Booking booking = bookingService.getBooking(bookingId);
        boolean isSuccess = paymentStrategy.processPayment(amount);

        if (isSuccess) {
            bookingService.confirmBooking(booking, user);
            System.out.println("Payment successful. Booking confirmed.");
        } else {
            System.out.println("Payment failed. Booking not confirmed for the booking id: " + bookingId);
        }
    }
}
