package SnakeLadder.utility;

public class GameBoard {
    private int width;
    private int height;
    private static GameBoard instance;

    private GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static GameBoard getInstance(int width, int height) {
        if (instance == null) {
            instance = new GameBoard(width, height);
        }
        return instance;
    }

    // getter
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
