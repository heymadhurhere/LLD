package BookMyShow.coreEntity;

import java.util.Date;
import BookMyShow.coreEntity.*;

// it brings the infra and movie together in time
public class Show {
    private final String id;
    private final Movie movie;
    private final Theatre theatre;
    private final Screen screen;
    private final Date startTime;
    private final int duration;

    //constructor
    public Show(String id, Movie movie, Theatre theatre, Screen screen, Date startTime, int duration) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public Screen getScreen() {
        return screen;
    }
}
