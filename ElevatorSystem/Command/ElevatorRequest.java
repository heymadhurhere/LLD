package ElevatorSystem.Command;

import ElevatorSystem.controllers.ElevatorController;
import ElevatorSystem.enums.Direction;


public class ElevatorRequest implements ElevatorCommand {
    private int elevatorId;
    private int floor;
    private Direction direction;
    private ElevatorController controller;
    private boolean isInternalRequest; // distinguishes between internal and external requests

    public ElevatorRequest(int elevatorId, int floor, boolean isInternalRequest, Direction direction) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternalRequest;
        this.direction = direction;
        this.controller = new ElevatorController();
    }

    @Override
    public void execute() {
        if (isInternalRequest) {
            controller.requestFloor(elevatorId, floor);
        }
        else {
            controller.requestElevator(elevatorId, floor, direction);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFloor() {
        return floor;
    }

    public boolean checkIsInternalequest() {
        return isInternalRequest;
    }
}