package SnakeLadder.controller;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import SnakeLadder.strategy.HumanMovementStrategy;
import SnakeLadder.strategy.MovementStrategy;
import SnakeLadder.utility.GameBoard;
import SnakeLadder.utility.Pair;

public class SnakeGame {
    private GameBoard gameBoard;
    public Deque<Pair> snake;
    private Map<Pair, Boolean> snakeMap;
    private int[][] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;

    public SnakeGame(int width, int height, int[][] food) {
        this.gameBoard = GameBoard.getInstance(width, height);
        this.food = food;
        this.foodIndex = 0;

        this.snake = new LinkedList<>();
        this.snakeMap = new HashMap<>();
        Pair initialPos = new Pair(0, 0);
        this.snake.offerFirst(initialPos);
        this.snakeMap.put(initialPos, true);

        this.movementStrategy = new HumanMovementStrategy();
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public int move(String direction) {
        Pair currentHead = this.snake.peekFirst();

        Pair newHead = this.movementStrategy.getNextPosition(currentHead, direction);

        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();

        // crosses boundary
        if (newHeadRow < 0 || newHeadRow >= gameBoard.getHeight() || newHeadCol < 0
                || newHeadCol >= gameBoard.getWidth()) {
            return -1;
        }

        Pair currentTail = this.snake.peekLast();

        // bites itself
        if (this.snakeMap.containsKey(newHead) && !newHead.equals(currentTail)) {
            return -1;
        }

        // eats food
        boolean ateFood = (this.foodIndex < this.food.length) && (newHeadRow == this.food[this.foodIndex][0])
                && (newHeadCol == this.food[this.foodIndex][1]);

        if (ateFood) {
            this.foodIndex++;
        } else {
            this.snake.pollLast();
            this.snakeMap.remove(currentTail);
        }

        this.snake.addFirst(newHead);
        this.snakeMap.put(newHead, true);

        int score = this.snake.size() - 1; // as snake started with size 1
        return score;
    }

    public Deque<Pair> getSnake() {
        return snake;
    }
}
