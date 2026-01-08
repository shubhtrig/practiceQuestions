package elevator.states;

import elevator.events.Event;
import elevator.events.FloorReachedEvent;
import elevator.Elevator;

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
            }
        }
    }
}
