package elevator.states;

import elevator.events.Event;
import elevator.events.FloorRequestedEvent;
import elevator.Elevator;
import elevator.Direction;

public class IdleState implements ElevatorState {
    @Override
    public void handleState(Elevator elevator, Event event) {
        if (event instanceof FloorRequestedEvent floorRequestedEvent) {
            System.out.println("Requested floor");
            int floor = floorRequestedEvent.floor();
            Direction direction = floorRequestedEvent.direction();
            elevator.requestFloor(floor, direction);
            elevator.setState(new MovingState());
        }
    }
}
