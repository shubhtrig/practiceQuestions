package elevator;

import java.util.List;

public class CostEffectiveElevatorStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, Direction direction, int floor) {
        int cost = Integer.MAX_VALUE;
        Elevator selectedElevator = null;

        for (Elevator elevator : elevators) {
            int currentCost = calculateCost(elevator, direction, floor);
            if (currentCost < cost) {
                cost = currentCost;
                selectedElevator = elevator;
            }
        }

        return null;
    }

    private int calculateCost(Elevator elevator, Direction direction, int floor) {
        int cost = Integer.MAX_VALUE;
        if (elevator.getMovingDirection() == Direction.IDLE) {
            cost = Math.abs(elevator.getCurrentFloor() - floor);
        } else if (elevator.getMovingDirection() == Direction.UP) {
            if (direction == Direction.UP && elevator.getCurrentFloor() <= floor) {
                cost = floor - elevator.getCurrentFloor();
            } else if (direction == Direction.UP && elevator.getCurrentFloor() > floor) {
                cost = 2* elevator.getMaxFloor() + elevator.getCurrentFloor() - floor;
            } else if (direction == Direction.DOWN) {
                cost = elevator.getMaxFloor() + Math.abs(elevator.getCurrentFloor() - floor);
            }
        } else if (elevator.getMovingDirection() == Direction.DOWN) {
            if (direction == Direction.DOWN && elevator.getCurrentFloor() >= floor) {
                cost = elevator.getCurrentFloor() - floor;
            } else if (direction == Direction.DOWN && elevator.getCurrentFloor() < floor) {
                cost = elevator.getCurrentFloor() + 2 * elevator.getMaxFloor() - floor;
            } else if (direction == Direction.UP) {
                cost = elevator.getMaxFloor() + Math.abs(elevator.getCurrentFloor() - floor);
            }
        }
        return cost;
    }
}
