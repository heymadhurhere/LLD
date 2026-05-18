package SnakeLadder.factory;

public class BonusFoodItem extends FoodItem {
    public BonusFoodItem(int row, int col) {
        super(row, col);
        this.points = 5;
    }
}
