package BookMyShow.services;

import java.util.*;

import BookMyShow.coreEntity.Theatre;
import BookMyShow.coreEntity.Screen;
import BookMyShow.coreEntity.Seat;
import BookMyShow.enums.SeatCategory;

public class TheatreService {
    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Theatre createTheatre(String name) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, name);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeatInScreen(int row, int seatNo, SeatCategory category, Screen screen) {
        String Id = UUID.randomUUID().toString();
        Seat seat = new Seat(Id, category, row, seatNo);
        seats.put(Id, seat);
        screen.addSeat(seat);
        return seat;
    }

    public Theatre getTheatre(String theatreId) {
        if (!theatres.containsKey(theatreId))
            throw new IllegalArgumentException("Theatre not found with ID: " + theatreId);
        return theatres.get(theatreId);
    }

    public Screen getScreen(String screenId) {
        if (!screens.containsKey(screenId))
            throw new IllegalArgumentException("Screen not found with ID: " + screenId);
        return screens.get(screenId);
    }

    public Seat getSeat(String seatId) {
        if (!seats.containsKey(seatId))
            throw new IllegalArgumentException("Seat not found with ID: " + seatId);
        return seats.get(seatId);
    }
}