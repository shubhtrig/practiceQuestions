import java.util.Map;

public class RateLimiterFactory {

    public static Limiter createRateLimiter(Map<String, Object> config) {
        String type = (String) config.get("type");
        Map<String, Object> algoConfig = (Map<String, Object>) config.get("algoConfig");
        if ("token_bucket".equals(type)) {
            int refillRatePerSecond = (int) algoConfig.get("refill_rate_per_second");
            int capacity = (int) algoConfig.get("capacity");
            return new TokenBucketLimiter(refillRatePerSecond, capacity);
        }

        if ("sliding_window".equals(type)) {
            int maxRequests = (int) algoConfig.get("max_requests");
            long windowSizeInMillis = ((Number) algoConfig.get("window_size_in_millis")).longValue();
            return new SlidingWindowLimiter(maxRequests, windowSizeInMillis);
        }

        // Additional rate limiter types can be added here
        throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
    }
}
