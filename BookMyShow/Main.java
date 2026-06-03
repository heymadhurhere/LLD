package BookMyShow;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import BookMyShow.coreEntity.*;
import BookMyShow.enums.*;
import BookMyShow.interfaces.*;
import BookMyShow.services.*;
import BookMyShow.concreteClasses.*;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("--- SYSTEM BOOTUP & WIRING ---");
            
            // 1. Initialize Core Services (Repositories)
            MovieService movieService = new MovieService();
            TheatreService theatreService = new TheatreService();
            ShowService showService = new ShowService();
            
            // 2. Initialize Concurrency Mechanisms
            // Lock expires in 120 seconds (2 minutes)
            ISeatLockProvider seatLockProvider = new SeatLockProvider(120); 
            
            // 3. Initialize Orchestration Services
            BookingService bookingService = new BookingService(seatLockProvider);
            SeatAvailabiltyService seatAvailabilityService = new SeatAvailabiltyService(bookingService, seatLockProvider);
            
            // Strategy Pattern injection: We decide to use Credit Cards today
            PaymentStrategy creditCardPayment = new CreditCardStrategy(); 
            PaymentService paymentService = new PaymentService(bookingService, creditCardPayment);

            // 4. Initialize Controllers
            controller controllerInstance = new controller();
            controller.MovieController movieController = controllerInstance.new MovieController(movieService);
            controller.TheatreController theatreController = controllerInstance.new TheatreController(theatreService);
            controller.ShowController showController = controllerInstance.new ShowController(showService, theatreService, movieService, seatAvailabilityService);
            controller.BookingController bookingController = controllerInstance.new BookingController(bookingService, showService, theatreService);
            controller.PaymentController paymentController = controllerInstance.new PaymentController(paymentService);

            
            System.out.println("\n--- 1. ADMIN SETUP ---");
            
            String movieId = movieController.createMovie("Inception", 148);
            System.out.println("Created Movie: Inception");

            String theatreId = theatreController.createTheatre("PVR Cinemas");
            String screenId = theatreController.createScreenInTheatre("Screen 1", theatreId);
            
            // Create just 3 seats for easy testing
            String seatId1 = theatreController.createSeatInScreen(1, 1, SeatCategory.GOLD, screenId);
            String seatId2 = theatreController.createSeatInScreen(1, 2, SeatCategory.GOLD, screenId);
            String seatId3 = theatreController.createSeatInScreen(1, 3, SeatCategory.GOLD, screenId);
            System.out.println("Created 3 Seats in Screen 1");

            String showId = showController.createShow(movieId, screenId, new Date(), 148);
            System.out.println("Scheduled Show for Inception");

            
            System.out.println("\n--- 2. USERS BROWSE ---");
            User user1 = new User("U1", "Alice", "alice@example.com");
            User user2 = new User("U2", "Bob", "bob@example.com");

            List<String> availableSeats = showController.getAvailableSeats(showId);
            System.out.println("Available Seats initially: " + availableSeats.size());


            System.out.println("\n--- 3. CONCURRENCY SIMULATION ---");
            System.out.println("Alice and Bob both try to book Seat 1 and Seat 2 at the exact same time!");

            List<String> desiredSeats = Arrays.asList(seatId1, seatId2);

            // Create two threads representing two separate web requests happening at once
            Thread threadA = new Thread(() -> {
                try {
                    System.out.println("Alice is attempting to lock seats...");
                    String bookingId = bookingController.createBooking(user1, showId, desiredSeats);
                    System.out.println("SUCCESS: Alice secured the lock! Booking ID: " + bookingId);
                    
                    // Alice pays successfully
                    paymentController.processPayment(bookingId, user1, 500.0);
                } catch (Exception e) {
                    System.out.println("FAILED for Alice: " + e.getMessage());
                }
            });

            Thread threadB = new Thread(() -> {
                try {
                    // Small sleep to ensure ThreadA starts just a microsecond earlier
                    Thread.sleep(10); 
                    System.out.println("Bob is attempting to lock seats...");
                    String bookingId = bookingController.createBooking(user2, showId, desiredSeats);
                    System.out.println("SUCCESS: Bob secured the lock! Booking ID: " + bookingId);
                } catch (Exception e) {
                    System.out.println("FAILED for Bob: " + e.getMessage());
                }
            });

            threadA.start();
            threadB.start();
            
            // Wait for both threads to finish
            threadA.join();
            threadB.join();

            System.out.println("\n--- 4. FINAL STATE ---");
            List<String> finalAvailableSeats = showController.getAvailableSeats(showId);
            System.out.println("Available Seats remaining: " + finalAvailableSeats.size());
            System.out.println("Remaining seat is: " + finalAvailableSeats.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}