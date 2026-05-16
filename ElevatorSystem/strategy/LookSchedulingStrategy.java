package ElevatorSystem.strategy;

import java.util.Queue;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.entity.Elevator;
import ElevatorSystem.enums.Direction;

public class LookSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requests = elevator.getRequestsQueue();

        if (requests == null || requests.isEmpty()) {
            return currentFloor;
        }

        ElevatorRequest primaryRequest = requests.peek();
        int primaryFloor = primaryRequest.getFloor();

        Direction travelDirection;
        if (primaryFloor > currentFloor) {
            travelDirection = Direction.UP;
        } else if (primaryFloor < currentFloor) {
            travelDirection = Direction.DOWN;
        } else {
            return currentFloor;
        }

        Integer candidate = null;

        for (ElevatorRequest req : requests) {
            int reqFloor = req.getFloor();
            if (travelDirection == Direction.UP && reqFloor > currentFloor && reqFloor <= primaryFloor) {
                if (req.checkIsInternalequest()
                        || (!req.checkIsInternalequest() && req.getDirection() == Direction.UP)) {
                    if (candidate == null || reqFloor < candidate) {
                        candidate = reqFloor;
                    }
                }
            } else if (travelDirection == Direction.DOWN && reqFloor < currentFloor && reqFloor >= primaryFloor) {
                if (req.checkIsInternalequest()
                        || (!req.checkIsInternalequest() && req.getDirection() == Direction.DOWN)) {
                    if (candidate == null || reqFloor > candidate) {
                        candidate = reqFloor;
                    }
                }
            }
        }

        return candidate != null ? candidate : primaryFloor;
    }
}
