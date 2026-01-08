package elevator.events;

public record FloorReachedEvent(int floor) implements Event {
}
