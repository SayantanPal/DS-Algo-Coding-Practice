

// Live API Rate Limiter: main class

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
// Goal: Limit each user to X requests per minute.

// HLD/Distributed Version - "How would this work across 10+ servers?"
// Redis-backed counters with INCR and EXPIRE
// Using Lua scripts to ensure atomicity
// Horizontal scaling using a shared datastore
// TTLs to avoid memory buildup
// Optional: Use Kafka or API Gateway for central control



class TockenBucketRateLimiter {
    private long lastRefillTimestamp;
    private int capacity, tokens;
    private final int refillRate;

    public TockenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
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
        int refillTokensToAdd = (int) ((now - lastRefillTimestamp) / 1_000_000_000 * refillRate);
        if (refillTokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + refillTokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}