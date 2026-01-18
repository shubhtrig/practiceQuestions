import java.util.HashMap;
import java.util.Map;

public class TokenBucketLimiter implements Limiter {
    private final int refillRatePerSecond;
    private final int capacity;
    private final Map<String, TokenBucket> userBuckets = new HashMap<>();

    public TokenBucketLimiter(int refillRatePerSecond, int capacity) {
        this.refillRatePerSecond = refillRatePerSecond;
        this.capacity = capacity;
    }

    @Override
    public RateLimiterResult isAllowed(String userId) {
        if (!userBuckets.containsKey(userId)) {
            userBuckets.put(userId, new TokenBucket(capacity, refillRatePerSecond));
        }

        TokenBucket bucket = userBuckets.get(userId);
        synchronized (bucket) {
            int leftoverTokens = bucket.getTokens();
            if (bucket.getTokens() > 0) {
                leftoverTokens = bucket.consume();
                return new RateLimiterResult(true, leftoverTokens, 0);
            }

            long waitTime = (long) (1000.0 / refillRatePerSecond);
            return new RateLimiterResult(false, leftoverTokens, waitTime);
        }
    }
}
