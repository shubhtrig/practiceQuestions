import java.time.Duration;
import java.time.Instant;

public class HourlyFixedPricingCalculation implements CalculationStrategy {
    private static final int hourlyRate = 10;
    @Override
    public int calculateParkingFee(Instant entryTime, Instant exitTime) {
        return Duration.between(entryTime, exitTime).toHoursPart() * hourlyRate;
    }
}
