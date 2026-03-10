package rateLimiter.V4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple token-bucket implementation (same idea as your V3).
 * Think of TokenBucketRateLimiter as “one bucket for one identity/user/ip.” Each identity has its own:
 * capacity (burst size)
 * refill rate
 * current token count
 * lastRefillTimestamp
 */
class TokenBucketRateLimiter {

    private final long capacity;         // max tokens
    private final double refillRate;     // tokens per second
    private double currentTokens;
    private long lastRefillTimestamp;    // in nanoseconds

    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketRateLimiter(long capacity, double refillRatePerSecond) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        if (refillRatePerSecond <= 0) throw new IllegalArgumentException("refillRate must be > 0");

        this.capacity = capacity;
        this.refillRate = refillRatePerSecond;
        this.currentTokens = capacity;   // start full
        this.lastRefillTimestamp = System.nanoTime();
    }

    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    public boolean tryAcquire(int tokens) {
        lock.lock();
        try {
            refill();
            if (currentTokens >= tokens) {
                currentTokens -= tokens;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillTimestamp) / 1_000_000_000.0;
        if (elapsedSeconds <= 0) {
            return;
        }
        double tokensToAdd = elapsedSeconds * refillRate;
        currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
        lastRefillTimestamp = now;
    }
}