package javaparactice.concurrency.a_practice.mar2026.ratelimiter.config;

/**
 * Immutable configuration for a token bucket.
 *
 * capacity: maximum burst size
 * refillTokensPerSecond: steady-state refill rate
 */
public record RateLimitConfig(int capacity, double refillTokensPerSecond) {

    public RateLimitConfig {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        if (refillTokensPerSecond <= 0.0d) {
            throw new IllegalArgumentException("refillTokensPerSecond must be greater than 0");
        }
    }
}
