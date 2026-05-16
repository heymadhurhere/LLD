package ElevatorSystem.Observer;

import ElevatorSystem.entity.Elevator;
import ElevatorSystem.enums.ElevatorState;

public class ElevatorDisplay implements ElevatorObserver {
    @Override
    public void onElevatorStateChange(Elevator elevator, ElevatorState state) {
        System.out.println("Elevator " + elevator.getId() + " state changed to: " + state);
    }

    @Override
    public void onELevatorFloorChange(Elevator elevator, int floor) {
        System.out.println("Elevator " + elevator.getId() + " is now at floor: " + floor);
    }

}
