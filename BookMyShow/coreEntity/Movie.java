package BookMyShow.coreEntity;

public class Movie {
    private final String id;
    private final String title;
    private final int duration;

    //constructor
    public Movie(String id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }
}
