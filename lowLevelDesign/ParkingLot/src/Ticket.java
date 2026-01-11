import java.time.Instant;

public class Ticket {
    private final String ticketId;
    private final int parkingSpotId;
    private final Instant entryTime;

    public Ticket(String ticketId, int parkingSpotId, Instant entryTime) {
        this.ticketId = ticketId;
        this.parkingSpotId = parkingSpotId;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public Instant getEntryTime() {
        return entryTime;
    }
}
