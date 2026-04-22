package javaparactice.concurrency.a_practice.mar2026.ratelimiter.type;

import javaparactice.concurrency.a_practice.mar2026.ratelimiter.NanoClock;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.RateLimitConfig;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.RateLimiter;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe token bucket rate limiter with lazy refill.
 *
 * Design notes for interview discussion:
 * - Uses a monotonic clock (`nanoTime`) instead of wall time.
 * - Preserves fractional tokens so low refill rates remain accurate.
 * - Uses a single lock so refill and consume happen atomically.
 * - Refills lazily on demand, avoiding a background maintenance thread.
 */
public final class TokenBucketRateLimiter implements RateLimiter {

    private static final NanoClock SYSTEM_NANO_CLOCK = System::nanoTime;

    private final RateLimitConfig config;
    private final NanoClock clock;
    private final ReentrantLock lock;

    // `availableTokens` is double so we do not lose fractional refill progress.
    private double availableTokens;
    // Tracks the point in monotonic time from which refill is measured.
    private long lastRefillNanos;

    public TokenBucketRateLimiter(RateLimitConfig config) {
        this(config, SYSTEM_NANO_CLOCK);
    }

    TokenBucketRateLimiter(RateLimitConfig config, NanoClock clock) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
        this.lock = new ReentrantLock();
        this.availableTokens = config.capacity();
        this.lastRefillNanos = clock.nanoTime();
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
            refillAt(clock.nanoTime());
            if (availableTokens < permits) {
                return false;
            }

            availableTokens -= permits;
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double getAvailableTokens() {
        lock.lock();
        try {
            refillAt(clock.nanoTime());
            return availableTokens;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long nanosUntilAvailable(int permits) {
        validatePermits(permits);

        lock.lock();
        try {
            refillAt(clock.nanoTime());
            if (availableTokens >= permits) {
                //token available. you can try now itself
                return 0L;
            }

            double missingTokens = permits - availableTokens;
            double nanosPerToken = 1_000_000_000.0d / config.refillTokensPerSecond();
            return (long) Math.ceil(missingTokens * nanosPerToken);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public RateLimitConfig getConfig() {
        return config;
    }

    /**
     * Refill based on elapsed monotonic time since the last refill point.
     * The bucket is capped at capacity to preserve true token bucket semantics.
     */
    private void refillAt(long nowNanos) {
        if (nowNanos <= lastRefillNanos) {
            return;
        }

        long elapsedNanos = nowNanos - lastRefillNanos;
        double tokensToAdd = elapsedNanos * config.refillTokensPerSecond() / 1_000_000_000.0d;
        if (tokensToAdd <= 0.0d) {
            return;
        }

        availableTokens = Math.min(config.capacity(), availableTokens + tokensToAdd);
        lastRefillNanos = nowNanos;
    }

    private static void validatePermits(int permits) {
        if (permits <= 0) {
            throw new IllegalArgumentException("permits must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return "TokenBucketRateLimiter{"
                + "capacity=" + config.capacity()
                + ", refillTokensPerSecond=" + config.refillTokensPerSecond()
                + ", availableTokens=" + getAvailableTokens()
                + '}';
    }
}
