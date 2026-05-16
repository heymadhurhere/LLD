package ElevatorSystem.Observer;

import ElevatorSystem.entity.Elevator;
import ElevatorSystem.enums.ElevatorState;

public interface ElevatorObserver {
    void onElevatorStateChange(Elevator elevator, ElevatorState state);
    void onELevatorFloorChange(Elevator elevator, int floor);
}
