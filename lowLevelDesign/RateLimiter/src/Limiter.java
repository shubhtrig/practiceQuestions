public interface Limiter {
    RateLimiterResult isAllowed(String userId);
}
