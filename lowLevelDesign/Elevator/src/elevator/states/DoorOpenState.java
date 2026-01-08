package elevator.states;

import elevator.Elevator;
import elevator.events.DoorTimeoutEvent;
import elevator.events.Event;

public class DoorOpenState implements ElevatorState {
    @Override
    public void handleState(Elevator elevator, Event event) {
        // Implementation for handling events when the door is open
        if (!(event instanceof DoorTimeoutEvent)) {
            return;
        }

        // Close the door after the timeout
        elevator.closeDoor();
        if (elevator.hasPendingRequests()) {
            elevator.setState(new MovingState());
        } else {
            elevator.setState(new IdleState());
        }
    }
}
