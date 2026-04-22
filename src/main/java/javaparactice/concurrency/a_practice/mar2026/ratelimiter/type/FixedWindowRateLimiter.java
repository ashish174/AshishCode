package javaparactice.concurrency.a_practice.mar2026.ratelimiter.type;

import javaparactice.concurrency.a_practice.mar2026.ratelimiter.NanoClock;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.RateLimitConfig;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.RateLimiter;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.WindowRateLimitConfig;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe fixed-window rate limiter.
 *
 * Design notes for interview discussion:
 * - Simple and efficient: O(1) memory and O(1) admission checks.
 * - Can admit bursts at window boundaries, which is the main tradeoff.
 * - Uses a monotonic clock so window rollover is not affected by wall-clock jumps.
 */
public final class FixedWindowRateLimiter implements RateLimiter {

    private static final NanoClock SYSTEM_NANO_CLOCK = System::nanoTime;

    private final WindowRateLimitConfig config;
    private final NanoClock clock;
    private final ReentrantLock lock;

    private long windowStartNanos;
    private int requestsInWindow;

    public FixedWindowRateLimiter(WindowRateLimitConfig config) {
        this(config, SYSTEM_NANO_CLOCK);
    }

    FixedWindowRateLimiter(WindowRateLimitConfig config, NanoClock clock) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
        this.lock = new ReentrantLock();
        this.windowStartNanos = clock.nanoTime();
        this.requestsInWindow = 0;
    }

    @Override
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    @Override
    public boolean tryAcquire(int permits) {
        validatePermits(permits);

        lock.lock();
        try {
            rollWindowIfNeeded(clock.nanoTime());
            if (requestsInWindow + permits > config.maxRequests()) {
                return false;
            }

            requestsInWindow += permits;
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double getAvailableTokens() {
        lock.lock();
        try {
            rollWindowIfNeeded(clock.nanoTime());
            return config.maxRequests() - requestsInWindow;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long nanosUntilAvailable(int permits) {
        validatePermits(permits);

        if (permits > config.maxRequests()) {
            return Long.MAX_VALUE;
        }

        lock.lock();
        try {
            long now = clock.nanoTime();
            rollWindowIfNeeded(now);
            if (requestsInWindow + permits <= config.maxRequests()) {
                return 0L;
            }

            long elapsedInWindow = now - windowStartNanos;
            return Math.max(0L, config.windowNanos() - elapsedInWindow);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public RateLimitConfig getConfig() {
        double permitsPerSecond = 1_000_000_000.0d * config.maxRequests() / config.windowNanos();
        return new RateLimitConfig(config.maxRequests(), permitsPerSecond);
    }

    private void rollWindowIfNeeded(long nowNanos) {
        long elapsed = nowNanos - windowStartNanos;
        if (elapsed < config.windowNanos()) {
            return;
        }

        long windowsElapsed = elapsed / config.windowNanos();
        windowStartNanos += windowsElapsed * config.windowNanos();
        requestsInWindow = 0;
    }

    private static void validatePermits(int permits) {
        if (permits <= 0) {
            throw new IllegalArgumentException("permits must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return "FixedWindowRateLimiter{"
                + "maxRequests=" + config.maxRequests()
                + ", window=" + config.window()
                + ", available=" + getAvailableTokens()
                + '}';
    }
}
