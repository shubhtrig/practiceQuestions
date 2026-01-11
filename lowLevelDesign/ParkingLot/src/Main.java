public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ParkingLot parkingLot = new ParkingLot(new HourlyFixedPricingCalculation());
        String ticketId = parkingLot.allotParkingSpot(VehicleType.CAR);
        if (ticketId == null) {
            System.out.println("No parking spot available");
            return;
        }
        System.out.println("Ticket ID: " + ticketId);

        int cost = parkingLot.getTotalCost(ticketId);
        System.out.println("Total Cost: " + cost);
    }
}