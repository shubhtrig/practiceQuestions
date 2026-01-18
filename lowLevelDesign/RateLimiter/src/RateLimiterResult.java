public record RateLimiterResult(boolean allowed, int remaining, long waitTimeMillis) {
}
