package SnakeLadder.strategy;

import SnakeLadder.utility.Pair;

public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}
