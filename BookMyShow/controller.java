package BookMyShow;

import BookMyShow.services.*;
import BookMyShow.coreEntity.*;
import BookMyShow.enums.*;
import java.util.*;


public class controller {
    public class MovieController {
        private final MovieService movieService;

        public MovieController(MovieService movieService) {
            this.movieService = movieService;
        }

        public String createMovie(String title, int duration) {
            return movieService.createMovie(title, duration).getId();
        }
    }

    public class TheatreController {
        private final TheatreService theatreService;

        public TheatreController(TheatreService theatreService) {
            this.theatreService = theatreService;
        }

        public String createTheatre(String name) {
            return theatreService.createTheatre(name).getId();
        }

        public String createScreenInTheatre(String screenName, String theatreId) {
            Theatre theatre = theatreService.getTheatre(theatreId);
            return theatreService.createScreenInTheatre(screenName, theatre).getId();
        }

        public String createSeatInScreen(int row, int seatNo, SeatCategory category, String screenId) {
            Screen screen = theatreService.getScreen(screenId);
            return theatreService.createSeatInScreen(row, seatNo, category, screen).getId();
        }
    }

    public class ShowController {
        private final ShowService showService;
        private final TheatreService theatreService;
        private final MovieService movieService;
        private final SeatAvailabiltyService seatAvailabiltyService;

        public ShowController(ShowService showService, TheatreService theatreService, MovieService movieService,
                SeatAvailabiltyService seatAvailabiltyService) {
            this.showService = showService;
            this.theatreService = theatreService;
            this.movieService = movieService;
            this.seatAvailabiltyService = seatAvailabiltyService;
        }

        public String createShow(String movieId, String screenId, Date startTime, int duration) {
            Screen screen = theatreService.getScreen(screenId);
            Movie movie = movieService.getMovie(movieId);
            return showService.CreateShow(movie, screen, startTime, duration, screen.getTheatre()).getId();    
        }

        public List<String> getAvailableSeats(String showId) {
            Show show = showService.getShow(showId);
            List<Seat> availableSeats = seatAvailabiltyService.getAvailableSeat(show);
            List<String> response = new ArrayList<>(); 
            for (Seat seat : availableSeats) {
                response.add(seat.getId());
            }
            return response;
        }
    }

    public class BookingController {
        private final BookingService bookingService;
        private final ShowService showService;
        private final TheatreService theatreService;

        public BookingController(BookingService bookingService, ShowService showService, TheatreService theatreService) {
            this.bookingService = bookingService;
            this.showService = showService;
            this.theatreService = theatreService;
        }

        public String createBooking(User user, String showId, List<String> seatIds) throws Exception {
            Show show = showService.getShow(showId);
            List<Seat> seats = new ArrayList<>(); 
            for (String seatId : seatIds) {
                seats.add(theatreService.getSeat(seatId));
            }
            return bookingService.createBooking(user, show, seats).getId();
        }
    }

    public class PaymentController {
        private final PaymentService paymentService;

        public PaymentController(PaymentService paymentService) {
            this.paymentService = paymentService;
        }

        public void processPayment(String bookingId, User user, double amount) throws Exception {
            paymentService.processPayment(bookingId, user, amount);
        }
    }
}