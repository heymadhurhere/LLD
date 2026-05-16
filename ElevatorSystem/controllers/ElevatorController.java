package ElevatorSystem.controllers;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.entity.Elevator;
import ElevatorSystem.entity.Floor;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.strategy.ScanSchedulingStrategy;
import ElevatorSystem.strategy.SchedulingStrategy;

import java.util.*;

public class ElevatorController {
    private List<Elevator> elevators = new ArrayList<>();
    private List<Floor> floors = new ArrayList<>();
    private SchedulingStrategy schedulingStrategy;
    private int currentElevatorId;

    public ElevatorController() {}

    public ElevatorController(int numberOfElevators, int numberOfFloors) {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.schedulingStrategy = new ScanSchedulingStrategy();

        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }

        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public void setSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
        this.schedulingStrategy = schedulingStrategy;
    }

    public void requestElevator(int elevatorId, int floorNumber, Direction direction) {
        System.out.println("Requesting Elevator " + elevatorId + " to floor " + floorNumber + " with direction " + direction);

        Elevator selectedElevator = getElevatorById(elevatorId);
        if (selectedElevator != null) {
            selectedElevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, false, direction));
            System.out.println("The elevator" + selectedElevator.getId() + " has been requested to floor " + floorNumber + " with direction " + direction);
        } else {
            System.out.println("No elevator available for the floor " + floorNumber);
        }
    }

    public void requestFloor(int elevatorId, int floorNumber) {
        Elevator elevator = getElevatorById(elevatorId);
        System.out.println("Internal request for elevator " + elevatorId + " to floor " + floorNumber);
        Direction direction = floorNumber > elevator.getCurrentFloor() ? Direction.UP : Direction.DOWN;

        elevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, true, direction));
    }

    private Elevator getElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                return elevator;
            }
        }
        return null;
    }

    public void step() {
        for (Elevator elevator : elevators) {
            if (!elevator.getRequestsQueue().isEmpty()) {
                int nextStop = schedulingStrategy.getNextStop(elevator);

                if (elevator.getCurrentFloor() != nextStop) {
                    elevator.moveToNextStop(nextStop);
                }
            }
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setCurrentElevator(int elevatorId) {
        this.currentElevatorId = elevatorId;
    }
}
