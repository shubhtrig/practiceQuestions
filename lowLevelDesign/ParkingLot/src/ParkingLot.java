import java.time.Instant;
import java.util.*;

public class ParkingLot {
    private final List<ParkingSpot> spots;
    private final Set<ParkingSpot> occupiedSpots;
    private final Map<String, Ticket> activeTickets;
    private final CalculationStrategy calculationStrategy;

    public ParkingLot(CalculationStrategy calculationStrategy) {
        this.spots = new ArrayList<>();
        spots.add(new ParkingSpot(1, ParkingSpotType.SMALL));
        spots.add(new ParkingSpot(2, ParkingSpotType.MEDIUM));
        spots.add(new ParkingSpot(3, ParkingSpotType.LARGE));
        this.occupiedSpots = Collections.synchronizedSet(new HashSet<>());
        this.activeTickets = new HashMap<>();
        this.calculationStrategy = calculationStrategy;
    }

    public String allotParkingSpot(VehicleType vehicleType) {
        ParkingSpotType requiredSpotType = getSpotTypeForVehicle(vehicleType);
        while (true) {
            ParkingSpot availableSpot = getAvailableSpot(requiredSpotType);
            if (availableSpot == null) {
                throw new IllegalStateException("No spots available");
            }

            if (occupiedSpots.add(availableSpot)) {
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket(ticketId, availableSpot.id(), Instant.now());
                activeTickets.put(ticketId, ticket);
                System.out.println("Allotted Spot ID: " + availableSpot.id() +
                        " for Vehicle Type: " +
                        vehicleType + " with Ticket ID: " + ticketId);
                return ticketId;
            }
        }
    }

    private ParkingSpot getAvailableSpot(ParkingSpotType spotType) {
        for (ParkingSpot spot : spots) {
            if (spot.type() == spotType && !occupiedSpots.contains(spot)) {
                return spot;
            }
        }
        return null;
    }

    public int getTotalCost(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket ID");
        }

        return calculationStrategy.calculateParkingFee(ticket.getEntryTime(), Instant.now());
    }

    private ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> ParkingSpotType.MEDIUM;
            case MOTORCYCLE -> ParkingSpotType.SMALL;
            case SUV -> ParkingSpotType.LARGE;
        };
    }
}
