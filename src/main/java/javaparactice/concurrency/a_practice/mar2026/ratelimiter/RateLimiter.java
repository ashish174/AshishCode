package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

/**
 * Core abstraction for a thread-safe rate limiter.
 *
 * A strong interview solution should expose a small, stable API and hide the
 * refill algorithm behind that abstraction.
 */
public interface RateLimiter {

    /**
     * Attempt to consume one permit immediately.
     *
     * @return {@code true} if the request is allowed, {@code false} otherwise
     */
    boolean tryAcquire();

    /**
     * Attempt to consume {@code permits} immediately.
     *
     * @param permits number of permits required by the request
     * @return {@code true} if the request is allowed, {@code false} otherwise
     */
    boolean tryAcquire(int permits);

    /**
     * Returns the current number of available tokens after applying lazy refill.
     * This is exact at the instant the call is executed under the limiter lock.
     */
    double getAvailableTokens();

    /**
     * Returns the estimated wait time before {@code permits} can be served.
     * Zero means the request can be admitted immediately.
     */
    long nanosUntilAvailable(int permits);

    /**
     * Exposes immutable configuration for observability and debugging.
     */
    RateLimitConfig getConfig();
}
