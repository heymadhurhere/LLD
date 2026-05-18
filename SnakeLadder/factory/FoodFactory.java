package SnakeLadder.factory;

public class FoodFactory {
    public static FoodItem createFood(int[] position, String type) {
        if (type.equals("bonus")) {
            return new BonusFoodItem(position[0], position[1]);
        }
        return new NormalFoodItem(position[0], position[1]);
    }
}
