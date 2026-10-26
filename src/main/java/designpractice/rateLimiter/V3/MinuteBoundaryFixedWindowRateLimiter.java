package designpractice.rateLimiter.V3;

import java.util.concurrent.TimeUnit;

/**
 * Thread-safe fixed window counter aligned to clock-minute boundaries.
 * Allows at most N tokens during each minute, such as [12:01:00, 12:02:00).
 * Each successful tryAcquire() consumes one token; rejected requests consume nothing.
 *
 * <p>Core idea: divide the current epoch time in milliseconds by 60,000 to get a
 * minute number. All timestamps in the same clock minute produce the same number.
 * When the minute number advances, reset the counter before checking the quota.
 * No timer or background thread is needed; reset happens on the next request.
 *
 * <p>Example with N = 3: if the limiter is created at 12:01:45, it allows three
 * tokens before 12:02:00. At exactly 12:02:00, a fresh quota of three is available.
 * The first window can therefore be shorter than 60 seconds.
 *
 * <p>Interview tradeoff: N tokens at 12:01:59 and another N at 12:02:00 are allowed.
 * This limits each clock minute, but does not enforce N tokens in every rolling
 * 60 seconds. Use SlidingWindowRateLimiter for that stricter requirement.
 *
 * <p>Time: O(1) per call. Space: O(1). One instance represents one shared quota
 * in this JVM; use separate instances for independent per-user limits.
 */
public class MinuteBoundaryFixedWindowRateLimiter {

    private static final long MILLIS_PER_MINUTE = TimeUnit.MINUTES.toMillis(1);

    private final int maxTokensPerMinute;
    private long windowMinute;
    private int usedTokens;

    /** @param maxTokensPerMinute maximum tokens allowed per clock minute; must be positive */
    public MinuteBoundaryFixedWindowRateLimiter(int maxTokensPerMinute) {
        if (maxTokensPerMinute <= 0) {
            throw new IllegalArgumentException("Max tokens per minute must be > 0");
        }
        this.maxTokensPerMinute = maxTokensPerMinute;
        this.windowMinute = System.currentTimeMillis() / MILLIS_PER_MINUTE;
    }

    /**
     * Attempts to consume one token immediately, without waiting for the next minute.
     *
     * @return true if allowed, false if the current minute has already used N tokens
     */
    public synchronized boolean tryAcquire() {
        // Use wall-clock time because windows must align to real minute boundaries.
        // For example, 12:01:00 and 12:01:59 belong to the same minute number.
        long currentMinute = System.currentTimeMillis() / MILLIS_PER_MINUTE;

        if (currentMinute > windowMinute) {
            // This also handles idle periods spanning several minutes: start fresh
            // in the current minute, without carrying forward unused tokens.
            windowMinute = currentMinute;
            usedTokens = 0;
        }
        // If the system clock moves backward, keep the existing count until time
        // catches up; moving backward must not grant an extra quota.

        if (usedTokens >= maxTokensPerMinute) {
            return false;
        }

        // Synchronization makes reset + check + increment atomic across callers.
        usedTokens++;
        return true;
    }

    /** Run this demo: within the same clock minute, only the first three calls succeed. */
    public static void main(String[] args) {
        MinuteBoundaryFixedWindowRateLimiter limiter = new MinuteBoundaryFixedWindowRateLimiter(3);
        for (int i = 1; i <= 5; i++) {
            System.out.printf("Request %d: %s%n", i, limiter.tryAcquire() ? "ALLOWED" : "DENIED");
        }
    }
}
