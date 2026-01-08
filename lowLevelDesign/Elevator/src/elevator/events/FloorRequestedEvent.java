package elevator.events;

import elevator.Direction;

public record FloorRequestedEvent(int floor, Direction direction) implements Event {
}
