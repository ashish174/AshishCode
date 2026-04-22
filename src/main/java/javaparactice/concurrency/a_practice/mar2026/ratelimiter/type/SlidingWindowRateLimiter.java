package javaparactice.concurrency.a_practice.mar2026.ratelimiter.type;

import javaparactice.concurrency.a_practice.mar2026.ratelimiter.NanoClock;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.RateLimitConfig;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.RateLimiter;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.WindowRateLimitConfig;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe sliding-window log rate limiter.
 *
 * Design notes for interview discussion:
 * - More precise than fixed window because every request is evaluated against the true rolling window.
 * - Uses a deque of accepted request timestamps.
 * - Tradeoff: memory grows with the allowed request volume in the current window.
 */
public final class SlidingWindowRateLimiter implements RateLimiter {

    private static final NanoClock SYSTEM_NANO_CLOCK = System::nanoTime;

    private final WindowRateLimitConfig config;
    private final NanoClock clock;
    private final ReentrantLock lock;
    private final Deque<Long> admittedRequestNanos;

    public SlidingWindowRateLimiter(WindowRateLimitConfig config) {
        this(config, SYSTEM_NANO_CLOCK);
    }

    SlidingWindowRateLimiter(WindowRateLimitConfig config, NanoClock clock) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
        this.lock = new ReentrantLock();
        this.admittedRequestNanos = new ArrayDeque<>();
    }

    @Override
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    @Override
    public boolean tryAcquire(int permits) {
        validatePermits(permits);

        if (permits > config.maxRequests()) {
            return false;
        }

        lock.lock();
        try {
            long now = clock.nanoTime();
            evictExpired(now);
            if (admittedRequestNanos.size() + permits > config.maxRequests()) {
                return false;
            }

            for (int i = 0; i < permits; i++) {
                admittedRequestNanos.addLast(now);
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double getAvailableTokens() {
        lock.lock();
        try {
            evictExpired(clock.nanoTime());
            return config.maxRequests() - admittedRequestNanos.size();
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
            evictExpired(now);
            if (admittedRequestNanos.size() + permits <= config.maxRequests()) {
                return 0L;
            }

            int indexToFreeEnoughCapacity = permits - 1;
            long oldestBlockingTimestamp = nthTimestamp(indexToFreeEnoughCapacity);
            long readyAt = oldestBlockingTimestamp + config.windowNanos();
            return Math.max(0L, readyAt - now);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public RateLimitConfig getConfig() {
        double permitsPerSecond = 1_000_000_000.0d * config.maxRequests() / config.windowNanos();
        return new RateLimitConfig(config.maxRequests(), permitsPerSecond);
    }

    private void evictExpired(long nowNanos) {
        long cutoff = nowNanos - config.windowNanos();
        while (!admittedRequestNanos.isEmpty() && admittedRequestNanos.peekFirst() <= cutoff) {
            admittedRequestNanos.removeFirst();
        }
    }

    private long nthTimestamp(int index) {
        int i = 0;
        for (Long timestamp : admittedRequestNanos) {
            if (i == index) {
                return timestamp;
            }
            i++;
        }
        throw new IllegalStateException("Expected timestamp index " + index + " to exist");
    }

    private static void validatePermits(int permits) {
        if (permits <= 0) {
            throw new IllegalArgumentException("permits must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return "SlidingWindowRateLimiter{"
                + "maxRequests=" + config.maxRequests()
                + ", window=" + config.window()
                + ", available=" + getAvailableTokens()
                + '}';
    }
}
