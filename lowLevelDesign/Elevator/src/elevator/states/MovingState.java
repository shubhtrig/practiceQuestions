package elevator.states;

import elevator.Direction;
import elevator.events.Event;
import elevator.events.FloorReachedEvent;
import elevator.Elevator;
import elevator.events.FloorRequestedEvent;

public class MovingState implements ElevatorState {
    @Override
    public void handleState(Elevator elevator, Event event) {
        if (event instanceof FloorReachedEvent floorReachedEvent) {
            System.out.println("Reached floor " + floorReachedEvent.floor());
            int floor = floorReachedEvent.floor();
            elevator.setCurrentFloor(floor);
            if (elevator.shouldStop()) {
                System.out.println("Stopper at floor " + floor);
                elevator.removeFloor(floor);
                elevator.setState(new DoorOpenState());
            } else {
                System.out.println("Did not stop at " + floor);
            }
        } else if (event instanceof FloorRequestedEvent floorRequestedEvent) {
            int floor = floorRequestedEvent.floor();
            Direction direction = floorRequestedEvent.direction();
            elevator.requestFloor(floor, direction);
            elevator.setState(new MovingState());
        }
    }
}
