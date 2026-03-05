package rateLimiter.V3;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe Rate Limiter using the Token Bucket Algorithm.
 *
 * Core Idea:
 *  - A bucket holds up to `capacity` tokens.
 *  - Tokens are added at a fixed `refillRate` (tokens per second).
 *  - Each request consumes 1 token. If no tokens are available, the request is rejected.
 *
 * Time Complexity:  O(1) per acquire()
 * Space Complexity: O(1)
 */
public class TokenBucketRateLimiter {

    private final long capacity;          // Max tokens the bucket can hold
    private final double refillRate;      // Tokens added per second
    private double currentTokens;         // Current available tokens
    private long lastRefillTimestamp;     // Last time tokens were refilled (nanoseconds)

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * @param capacity   Maximum number of tokens (burst size)
     * @param refillRate Tokens added per second
     */
    public TokenBucketRateLimiter(long capacity, double refillRate) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        if (refillRate <= 0) throw new IllegalArgumentException("Refill rate must be > 0");

        this.capacity = capacity;
        this.refillRate = refillRate;
        this.currentTokens = capacity;           // Start with a full bucket
        this.lastRefillTimestamp = System.nanoTime();
    }

    /**
     * Attempts to acquire 1 token.
     *
     * @return true if the request is allowed, false if rate-limited
     */
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    /**
     * Attempts to acquire `tokens` tokens atomically.
     *
     * @param tokens Number of tokens to consume
     * @return true if allowed, false if rate-limited
     */
    public boolean tryAcquire(int tokens) {
        lock.lock();
        try {
            refill();

            if (currentTokens >= tokens) {
                currentTokens -= tokens;
                return true;   // Request allowed
            }

            return false;      // Not enough tokens → rate limited
        } finally {
            lock.unlock();
        }
    }

    /**
     * Refills tokens based on elapsed time since last refill.
     * Called internally before every acquire attempt.
     */
    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillTimestamp) / 1_000_000_000.0;

        double tokensToAdd = elapsedSeconds * refillRate;
        currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
        lastRefillTimestamp = now;
    }

    /** Returns current available tokens (snapshot, approximate in concurrent settings). */
    public double getAvailableTokens() {
        lock.lock();
        try {
            refill();
            return currentTokens;
        } finally {
            lock.unlock();
        }
    }

    // ─────────────────────────────────────────────────────────
    // Demo / Test
    // ─────────────────────────────────────────────────────────

    public static void main(String[] args) throws InterruptedException {

        // Allow burst of 5, then refill at 2 tokens/second
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 2.0);

        System.out.println("=== Burst Test: 8 rapid requests (bucket has 5 tokens) ===");
        for (int i = 1; i <= 8; i++) {
            boolean allowed = limiter.tryAcquire();
            System.out.printf("Request %d: %s%n", i, allowed ? "✅ ALLOWED" : "❌ DENIED");
        }

        System.out.println("\n--- Waiting 2 seconds (expect ~4 new tokens) ---\n");
        Thread.sleep(2000);

        System.out.println("=== After refill: 4 rapid requests ===");
        for (int i = 1; i <= 4; i++) {
            boolean allowed = limiter.tryAcquire();
            System.out.printf("Request %d: %s%n", i, allowed ? "✅ ALLOWED" : "❌ DENIED");
        }

        System.out.println("\n=== Multi-token acquire test: request 3 tokens at once ===");
        Thread.sleep(2000); // refill ~4 tokens
        boolean bigRequest = limiter.tryAcquire(3);
        System.out.println("Acquire 3 tokens: " + (bigRequest ? "✅ ALLOWED" : "❌ DENIED"));

        System.out.println("\nAvailable tokens: " + String.format("%.2f", limiter.getAvailableTokens()));
    }
}


// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW DISCUSSION NOTES (read before your interview!)
// ─────────────────────────────────────────────────────────────────────────────
//
// WHY TOKEN BUCKET?
//   ✔ Handles bursts naturally (up to `capacity` burst size)
//   ✔ Smooth average rate enforcement via token refill
//   ✔ O(1) time and O(1) space — no request history needed
//
// HOW IT WORKS:
//   1. Bucket starts full (capacity tokens).
//   2. Each request consumes 1 token (or N tokens for weighted requests).
//   3. Tokens refill continuously at `refillRate` tokens/sec.
//   4. If bucket is empty → request is denied (rate limited).
//
// THREAD SAFETY:
//   - ReentrantLock ensures refill + consume is atomic.
//   - Alternative: AtomicLong with CAS loop (lock-free, higher throughput).
//
// ALTERNATIVE ALGORITHMS (know these for interviews):
//   ┌──────────────────────┬───────────────────────────────────────────────┐
//   │ Algorithm            │ Key Difference                                │
//   ├──────────────────────┼───────────────────────────────────────────────┤
//   │ Token Bucket         │ Allows bursts; smooth average rate            │
//   │ Leaky Bucket         │ No bursts; enforces strict constant output    │
//   │ Fixed Window Counter │ Simple; susceptible to edge-case bursts       │
//   │ Sliding Window Log   │ Most accurate; high memory per user           │
//   │ Sliding Window Counter│ Good balance of accuracy and memory          │
//   └──────────────────────┴───────────────────────────────────────────────┘
//
// DISTRIBUTED RATE LIMITING (follow-up question):
//   - Use Redis with Lua scripts for atomic check-and-decrement.
//   - Redis key per user: "rate_limit:{userId}" → token count + timestamp.
//   - Lua ensures atomicity without distributed locks.
//
// REAL-WORLD USAGE:
//   - API Gateways (AWS API Gateway, Kong, Nginx)
//   - Google Guava: RateLimiter class uses token bucket internally
//   - Resilience4j: RateLimiter module