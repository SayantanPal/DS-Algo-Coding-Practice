

// RateLimiter: main class

// LLD
// TokenBucket: tracks tokens and refill rate
// ScheduledExecutorService to refill tokens periodically
// Thread-safety with AtomicInteger
// Time-based refill logic

// HLD
// Redis-backed counters
// Using Lua scripts to ensure atomicity
// Horizontal scaling using a shared datastore
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