package elevator;

import elevator.events.Event;
import elevator.states.ElevatorState;
import elevator.states.IdleState;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Elevator implements Runnable {
    private final int id;
    private int currentFloor = 0;
    private final Direction movingDirection = Direction.UP;
    private final Set<Request> requests = new HashSet<>();
    private ElevatorState state;
    private final Set<Integer> upwardStops = new HashSet<>();
    private final Set<Integer> downStops = new HashSet<>();
    private final BlockingDeque<Event> eventQueue;

    public Elevator(int id) {
        this.id = id;
        state = new IdleState();
        eventQueue = new LinkedBlockingDeque<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getMovingDirection() {
        return movingDirection;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public boolean shouldStop() {
        System.out.println(upwardStops);
        if (movingDirection == Direction.UP) {
            return upwardStops.contains(currentFloor);
        } else {
            return downStops.contains(currentFloor);
        }
    }

    public void requestFloor(int floor, Direction direction) {
        if (floor < 0 || floor > 9) {
            throw new IllegalArgumentException("Floor must be between 0 and 9");
        }

        requests.add(new Request(floor, direction));
        if (direction == Direction.UP) {
            upwardStops.add(floor);
        } else {
            downStops.add(floor);
        }

        // Logic to handle floor request
        System.out.println("Elevator " + id + " requested to floor " + floor + " going " + direction);
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Elevator " + id + " waiting for event...");
                Event event = eventQueue.take();
                state.handleState(this, event);
            } catch (InterruptedException e) {
                System.out.println("Elevator " + id + " interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void addEvent(Event event) {
        eventQueue.offer(event);
    }

    public void removeFloor(int floor) {
        if (movingDirection == Direction.UP) {
            System.out.println("Elevator " + id + " removed floor " + floor);
            upwardStops.remove(floor);
        } else {
            downStops.remove(floor);
        }
    }

    public boolean hasPendingRequests() {
        return !requests.isEmpty();
    }

    public void closeDoor() {
        System.out.println("Elevator " + id + " door is closing.");
    }

    public int getId() {
        return id;
    }
}
