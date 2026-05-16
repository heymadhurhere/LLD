package ElevatorSystem.entity;

import ElevatorSystem.enums.Direction;
import ElevatorSystem.enums.ElevatorState;
import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Observer.ElevatorDisplay;
import ElevatorSystem.Observer.ElevatorObserver;

import java.util.*;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private List<ElevatorObserver> observers;
    private Queue<ElevatorRequest> requests;
    private ElevatorState state;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.observers = new ArrayList<>();
        this.requests = new LinkedList<>();
        this.state = ElevatorState.IDLE;
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    public void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorStateChange(this, state);
        }
    }

    public void notifyFloorChange(int floor) {
        for (ElevatorObserver observer : observers) {
            observer.onELevatorFloorChange(this, floor);
        }
    }

    public void setState(ElevatorState newState) {
        this.state = newState;
        notifyStateChange(newState);
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public void addRequest(ElevatorRequest elevatorRequest) {
        if (!requests.contains(elevatorRequest)) {
            requests.add(elevatorRequest);
        }

        int requestedFloor = elevatorRequest.getFloor();

        if (state == ElevatorState.IDLE && !requests.isEmpty()) {
            if (requestedFloor > currentFloor) {
                direction = Direction.UP;
            } else if (requestedFloor < currentFloor) {
                direction = Direction.DOWN;
            }
            setState(ElevatorState.MOVING);
        }
    }

    public void moveToNextStop(int nextStop) {
        if (state != ElevatorState.MOVING) return;

        while (currentFloor != nextStop) {
            if (direction == Direction.UP) {
                currentFloor++;
                notifyFloorChange(currentFloor);
            } else {
                currentFloor--;
                notifyFloorChange(currentFloor);
            }

            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }

    public void completeArrival() {
        setState(ElevatorState.STOPPED);

        requests.removeIf(requests -> requests.getFloor() == currentFloor);

        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            setState(ElevatorState.MOVING);
        }
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }

    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }
}
