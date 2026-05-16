package ElevatorSystem.strategy;

import ElevatorSystem.entity.Elevator;

public interface SchedulingStrategy {
    int getNextStop(Elevator elevator);
}
