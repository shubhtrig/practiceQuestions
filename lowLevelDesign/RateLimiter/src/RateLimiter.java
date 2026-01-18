import java.util.List;
import java.util.Map;

public class RateLimiter {
    private final Map<String, Limiter> limiters;

    public RateLimiter(List<Map<String, Object>> configs) {
        limiters = new java.util.HashMap<>();
        for (Map<String, Object> config : configs) {
            String name = (String) config.get("endpoint");
            Limiter limiter = RateLimiterFactory.createRateLimiter(config);
            limiters.put(name, limiter);
        }
    }

    public RateLimiterResult isAllowed(Request request) {
        Limiter limiter = limiters.get(request.endpoint());
        if (limiter == null) {
            throw new IllegalArgumentException("No rate limiter configured for endpoint: " + request.endpoint());
        }
        return limiter.isAllowed(request.clientId());
    }
}
