package BookMyShow.coreEntity;

import BookMyShow.enums.SeatCategory;

public class Seat {
    private final String id;
    private final SeatCategory category;
    private final int row;
    private final int column;

    //constructor
    public Seat(String id, SeatCategory category, int row, int column) {
        this.id = id;
        this.category = category;
        this.row = row;
        this.column = column;
    }

    public String getId() {
        return id;
    }
}
