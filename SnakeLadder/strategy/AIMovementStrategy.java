package SnakeLadder.strategy;

import SnakeLadder.utility.Pair;

public class AIMovementStrategy implements MovementStrategy {
    @Override
    public Pair getNextPosition(Pair currentHead, String direction) {
        return currentHead;
    }
}
