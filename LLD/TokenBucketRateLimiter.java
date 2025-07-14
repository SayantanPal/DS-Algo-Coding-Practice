class TockenBucketRateLimiter {
    private long lastRefillTime;
    private int capacity, tokens;
    private final int refillRate;

    public TockenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTime = System.nanoTime();
    }
    public synchronized boolean allowRequest() {
        refill();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
    private void refill() {
        long now = System.nanoTime();
        int tokensToAdd = (int) ((now - lastRefillTime) / 1_000_000_000 * refillRate);
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTime = now;
        }
    }
}