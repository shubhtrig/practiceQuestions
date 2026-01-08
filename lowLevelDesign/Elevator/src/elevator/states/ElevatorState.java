package elevator.states;

import elevator.events.Event;
import elevator.Elevator;

public interface ElevatorState {
    void handleState(Elevator elevator, Event event);
}
