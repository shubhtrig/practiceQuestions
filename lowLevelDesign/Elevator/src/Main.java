import elevator.ElevatorController;
import elevator.NearestElevatorStrategy;
import elevator.events.DoorTimeoutEvent;
import elevator.events.FloorReachedEvent;

public class Main {
    public static void main(String[] args) {

        ElevatorController controller = new ElevatorController(new NearestElevatorStrategy());
        controller.requestElevator(3, elevator.Direction.UP);
        controller.requestElevator(5, elevator.Direction.UP);

        controller.addElevatorEvent(1, new FloorReachedEvent(3));
        controller.addElevatorEvent(1, new DoorTimeoutEvent());
        controller.addElevatorEvent(1, new FloorReachedEvent(5));
        controller.addElevatorEvent(1, new DoorTimeoutEvent());
    }
}