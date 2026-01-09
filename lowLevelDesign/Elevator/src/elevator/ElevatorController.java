package elevator;

import elevator.events.Event;

import java.util.List;

public class ElevatorController {
    private final List<Elevator> elevators;
    private final ElevatorSelectionStrategy strategy;

    public ElevatorController(ElevatorSelectionStrategy strategy) {
        this.elevators = List.of(new Elevator(1), new Elevator(2), new Elevator(3));
        elevators.forEach(elevator -> new Thread(elevator).start());
        this.strategy = strategy;
    }

    public void requestElevator(int floor, Direction direction) {
        if (floor < 0 || floor > 9) {
            throw new IllegalArgumentException("Floor must be between 0 and 9");
        }

        System.out.println("Elevator requested at floor " + floor + " going " + direction);
        Elevator elevator = strategy.selectElevator(this.elevators, direction, floor);
        elevator.addEvent(new elevator.events.FloorRequestedEvent(floor, direction));
        System.out.println("Elevator " + elevator.getId() + " assigned to floor " + floor);
    }

    public void addElevatorEvent(int id, Event event) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == id) {
                elevator.addEvent(event);
                break;
            }
        }
    }
}
