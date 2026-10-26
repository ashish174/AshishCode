package designpractice.rateLimiter.V3;

import java.util.concurrent.TimeUnit;

/**
 * Thread-safe fixed window counter: allows at most N tokens per fixed 60-second window.
 * Each successful tryAcquire() consumes one token; rejected requests consume nothing.
 *
 * <p>Core idea: keep one counter for the current window. When a request arrives in
 * a later window, reset the counter before checking the limit. No background timer
 * is needed; reset happens lazily on the next request.
 *
 * <p>Windows are anchored to instance creation: [0, 60), [60, 120), etc. They are
 * elapsed-time minutes, not calendar minutes such as 12:01:00 through 12:01:59.
 *
 * <p>Important interview tradeoff: N requests at second 59 and another N at second
 * 60 are allowed. Each fixed window respects its quota, but nearly 2N requests can
 * pass close to a boundary. Use SlidingWindowRateLimiter when the requirement is
 * at most N tokens in ANY rolling minute.
 *
 * <p>Time: O(1) per call. Space: O(1), since no request history is stored.
 * One instance represents one shared quota in this JVM; use separate instances
 * per user if users should have independent limits.
 */
public class FixedWindowRateLimiter {

    private static final long WINDOW_NANOS = TimeUnit.MINUTES.toNanos(1);

    private final int maxTokensPerMinute;
    private long windowStartNanos;
    private int usedTokens;

    /** @param maxTokensPerMinute maximum tokens allowed per fixed minute; must be positive */
    public FixedWindowRateLimiter(int maxTokensPerMinute) {
        if (maxTokensPerMinute <= 0) {
            throw new IllegalArgumentException("Max tokens per minute must be > 0");
        }
        this.maxTokensPerMinute = maxTokensPerMinute;
        this.windowStartNanos = System.nanoTime();
    }

    /**
     * Attempts to consume one token immediately, without waiting for the next window.
     *
     * @return true if allowed, false if the current fixed minute already used N tokens
     */
    public synchronized boolean tryAcquire() {
        // nanoTime measures elapsed time and is unaffected by wall-clock corrections.
        long now = System.nanoTime();
        long elapsed = now - windowStartNanos;

        if (elapsed >= WINDOW_NANOS) {
            // Skip all completed windows, even after a long idle period.
            // Keep the original boundaries: at second 125, the window starts at 120,
            // not 125. Simply setting the start to 'now' would shift the boundaries.
            windowStartNanos = now - (elapsed % WINDOW_NANOS);
            usedTokens = 0;
        }

        if (usedTokens >= maxTokensPerMinute) {
            return false;
        }

        // Reset + check + increment are atomic, so concurrent callers cannot exceed N.
        usedTokens++;
        return true;
    }

    /** Run this demo: the first three requests succeed and the remaining two fail. */
    public static void main(String[] args) {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(3);
        for (int i = 1; i <= 5; i++) {
            System.out.printf("Request %d: %s%n", i, limiter.tryAcquire() ? "ALLOWED" : "DENIED");
        }
    }
}
