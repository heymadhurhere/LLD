package SnakeLadder.factory;

public abstract class FoodItem {
    protected int row, column;
    protected int points;

    public FoodItem(int row, int col) {
        this.row = row;
        this.column = col;
    }

    // getters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }

    public int getPoints() {
        return points;
    }
}
