package BookMyShow.services;

import java.util.*;

import BookMyShow.coreEntity.Show;
import BookMyShow.coreEntity.Movie;
import BookMyShow.coreEntity.Screen;
import BookMyShow.coreEntity.Theatre;

public class ShowService {
    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show CreateShow(Movie movie, Screen screen, Date startTime, int duration, Theatre theatre) {
        String Id = UUID.randomUUID().toString();
        Show show = new Show(Id, movie, theatre, screen, startTime, duration);
        shows.put(Id, show);
        return show;
    }

    public Show getShow(String showId) {
        if (!shows.containsKey(showId)) {
            throw new IllegalArgumentException("Show not found");
        }
        return shows.get(showId);
    }

    public List<Show> getShowsForScreen(Screen screen) {
        List<Show> response = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().getId().equals(screen.getId())) {
                response.add(show);
            }
        }
        return response;
    }
}
