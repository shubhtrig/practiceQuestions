import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SlidingWindowLimiter implements Limiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final Map<String, SlidingWindow> userWindows = new java.util.HashMap<>();

    public SlidingWindowLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public RateLimiterResult isAllowed(String userId) {
        long now = System.currentTimeMillis();
        SlidingWindow window = userWindows.computeIfAbsent(userId, k -> new SlidingWindow());

        while (!window.timestamps.isEmpty() && window.timestamps.peek() < now - windowSizeInMillis) {
            window.timestamps.poll();
        }

        if (window.timestamps.size() < maxRequests) {
            window.timestamps.add(now);
            return new RateLimiterResult(true, maxRequests - window.timestamps.size(), 0);
        } else {
            long waitTime = window.timestamps.peek() + windowSizeInMillis - now;
            return new RateLimiterResult(false, 0, waitTime);
        }
    }

    private static class SlidingWindow {
        private final Queue<Long> timestamps = new LinkedList<>();
    }
}
