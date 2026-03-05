package rateLimiter.V4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Token Bucket implementation of {@link RateLimiter}.
 *
 * Algorithm:
 *  - Bucket holds up to `capacity` tokens (burst size).
 *  - Tokens refill continuously at `refillRate` tokens/second.
 *  - Each request consumes tokens; denied if insufficient.
 *
 * Thread Safety: ReentrantLock makes refill+consume atomic.
 * Time Complexity: O(1)  |  Space Complexity: O(1)
 */
public final class TokenBucketRateLimiter implements RateLimiter {

    private final RateLimitConfig config;
    private double currentTokens;
    private long lastRefillNanos;
    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        if (config == null) throw new IllegalArgumentException("Config must not be null");
        this.config = config;
        this.currentTokens = config.getCapacity();   // start full
        this.lastRefillNanos = System.nanoTime();
    }

    @Override
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    @Override
    public boolean tryAcquire(int tokens) {
        if (tokens <= 0) throw new IllegalArgumentException("Tokens must be > 0");
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

    @Override
    public double getAvailableTokens() {
        lock.lock();
        try {
            refill();
            return currentTokens;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public RateLimitConfig getConfig() {
        return config;
    }

    /** Lazy refill — called before every acquire. No background threads needed. */
    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillNanos) / 1_000_000_000.0;
        double tokensToAdd = elapsedSeconds * config.getRefillRate();
        currentTokens = Math.min(config.getCapacity(), currentTokens + tokensToAdd);
        lastRefillNanos = now;
    }

    @Override
    public String toString() {
        return String.format("TokenBucketRateLimiter{config=%s, availableTokens=%.2f}",
                config, currentTokens);
    }
}
