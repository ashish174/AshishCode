package rateLimiter.V5;

/**
 * Strategy interface for rate limiting algorithms.
 *
 * Design: Strategy Pattern — callers depend on this abstraction.
 * Swap Token Bucket for Sliding Window, Leaky Bucket, etc. without
 * touching any calling code (Open/Closed Principle).
 */
public interface RateLimiter {

    /**
     * Attempt to consume 1 token.
     *
     * @return true if the request is allowed, false if rate-limited
     */
    boolean tryAcquire();

    /**
     * Attempt to consume {@code tokens} tokens (for weighted requests).
     *
     * @param tokens number of tokens to consume
     * @return true if allowed, false if rate-limited
     */
    boolean tryAcquire(int tokens);

    /** Snapshot of currently available tokens. Approximate in concurrent use. */
    double getAvailableTokens();

    /** The config this limiter was created from. */
    RateLimitConfig getConfig();
}
