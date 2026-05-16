package ElevatorSystem.strategy;

import ElevatorSystem.entity.Elevator;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.Command.ElevatorRequest;

import java.util.*;

public class FCFSSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        Direction elevatorDirection = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requestQueue = elevator.getRequestsQueue();

        if (requestQueue.isEmpty()) {
            return currentFloor;
        }

        int nextRequestedFloor = requestQueue.poll().getFloor();

        if (nextRequestedFloor == currentFloor) {
            return currentFloor;
        }

        if (elevatorDirection == Direction.IDLE) {
            elevator.setDirection(nextRequestedFloor > currentFloor ? Direction.UP : Direction.DOWN);
        } else if (elevatorDirection == Direction.UP && nextRequestedFloor < currentFloor) {
            elevator.setDirection(Direction.DOWN);
        } else if (elevatorDirection == Direction.DOWN && nextRequestedFloor > currentFloor) {
            elevator.setDirection(Direction.UP);
        }

        return nextRequestedFloor;
    }
}
