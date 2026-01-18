public class TokenBucket {
    private final int capacity;
    private final int refillRatePerSecond;
    private int tokens;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
    }

    public int getTokens() {
        refill();
        return tokens;
    }

    public int consume() {
        return --tokens;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTimestamp;

        int tokensToAdd = (int) (elapsedTime / 1000) * refillRatePerSecond;
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
