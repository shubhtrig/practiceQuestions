import java.time.Instant;

public interface CalculationStrategy {
    int calculateParkingFee(Instant entryTime, Instant exitTime);
}
