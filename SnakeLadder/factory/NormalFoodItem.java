package SnakeLadder.factory;

public class NormalFoodItem extends FoodItem {
    public NormalFoodItem(int row, int col) {
        super(row, col);
        this.points = 1;
    }
}
