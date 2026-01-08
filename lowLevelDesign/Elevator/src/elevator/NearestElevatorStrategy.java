package elevator;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Direction direction, int floor) {
        int nearestDistance = Integer.MAX_VALUE;
        Elevator selectedElevator = null;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - floor);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                selectedElevator = elevator;
            }
        }

        return selectedElevator;
    }
}
