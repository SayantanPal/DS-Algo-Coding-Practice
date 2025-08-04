

// RateLimiter: main class

/*
* Problem Statement: You have a service that receives thousands of requests per second.
* You want to limit each user to 100 requests per minute.
 * */

/*
* Requirements:
     - Allow only X requests per Y seconds per user
     - Needs to be thread-safe
     - Handle multiple users
* Approach:
    - Use Token Bucket or Leaky Bucket Algorithm
    - Store per-user counters with timestamps (use Redis or in-memory store) or HashMap of <UserId, Queue<Timestamps>>
    - Each request checks if the timestamp queue has more than X timestamps in the last Y seconds.
    - If yes, block the request.
    - Use Sliding Window Counter or Token Bucket algorithm
*
Java Concept: Use ConcurrentHashMap<String, Queue<Long>> to store timestamps of requests.
* */
// Companies - Adobe
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