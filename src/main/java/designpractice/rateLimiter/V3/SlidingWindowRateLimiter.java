package designpractice.rateLimiter.V3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Thread-safe sliding window log: allows at most N tokens in any rolling 60 seconds.
 * Each successful tryAcquire() consumes one token; rejected requests consume nothing.
 *
 * <p>Core idea: keep the timestamps of accepted requests in oldest-first order.
 * Before allowing a request, remove timestamps that are at least 60 seconds old.
 * Allow the request only if fewer than N timestamps remain.
 *
 * <p>Example with N = 3: requests at seconds 0, 10 and 20 are allowed. A request
 * at second 59 is denied. At second 60, the request at second 0 expires, so one
 * new request can be allowed.
 * The active interval is (now - 60 seconds, now].
 *
 * <p>Interview notes:
 * <ul>
 *   <li>This is an exact sliding window log, not an approximate sliding window counter.</li>
 *   <li>Time: amortized O(1) per call, since each timestamp is added and removed once.
 *       A single call can take O(N) when many old entries expire together.</li>
 *   <li>Space: O(N), since only accepted requests in the window are stored.</li>
 *   <li>One instance represents one shared quota. Use separate instances per user
 *       if users should have independent limits. State is local to this JVM.</li>
 * </ul>
 */
public class SlidingWindowRateLimiter {

    private static final long WINDOW_NANOS = TimeUnit.MINUTES.toNanos(1);

    private final int maxTokensPerMinute;
    private final Deque<Long> acceptedTimestamps = new ArrayDeque<>();

    /** @param maxTokensPerMinute maximum tokens allowed in any rolling minute; must be positive */
    public SlidingWindowRateLimiter(int maxTokensPerMinute) {
        if (maxTokensPerMinute <= 0) {
            throw new IllegalArgumentException("Max tokens per minute must be > 0");
        }
        this.maxTokensPerMinute = maxTokensPerMinute;
    }

    /**
     * Attempts to consume one token immediately, without waiting for capacity.
     *
     * @return true if allowed, false if the rolling minute already contains N tokens
     */
    public synchronized boolean tryAcquire() {
        // Read time inside the lock so concurrent callers keep timestamps in order.
        // nanoTime measures elapsed time and is unaffected by wall-clock corrections.
        long now = System.nanoTime();

        // A request exactly 60 seconds old is outside the current rolling window.
        while (!acceptedTimestamps.isEmpty()
                && now - acceptedTimestamps.peekFirst() >= WINDOW_NANOS) {
            acceptedTimestamps.removeFirst();
        }

        if (acceptedTimestamps.size() >= maxTokensPerMinute) {
            return false;
        }

        // Cleanup + check + insert are atomic because the whole method is synchronized.
        acceptedTimestamps.addLast(now);
        return true;
    }

    /** Run this demo: the first three requests succeed and the remaining two fail. */
    public static void main(String[] args) {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(3);
        for (int i = 1; i <= 5; i++) {
            System.out.printf("Request %d: %s%n", i, limiter.tryAcquire() ? "ALLOWED" : "DENIED");
        }
    }
}
