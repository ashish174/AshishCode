package rateLimiter.V2;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A custom Rate Limiter that limits requests based on RPS (Requests per Second),
 * RPM (Requests per Minute), RPH (Requests per Hour), and RPD (Requests per Day).
 */
public class RateLimiter {
    private final int rpsLimit;
    private final int rpmLimit;
    private final int rphLimit;
    private final int rpdLimit;
    private final ConcurrentHashMap<String, AtomicInteger> requestCounts;
    private Instant lastResetTime;

    /**
     * Constructor to initialize the rate limiter with specified limits.
     *
     * @param rpsLimit Requests per second limit
     * @param rpmLimit Requests per minute limit
     * @param rphLimit Requests per hour limit
     * @param rpdLimit Requests per day limit
     */
    public RateLimiter(int rpsLimit, int rpmLimit, int rphLimit, int rpdLimit) {
        this.rpsLimit = rpsLimit;
        this.rpmLimit = rpmLimit;
        this.rphLimit = rphLimit;
        this.rpdLimit = rpdLimit;
        this.requestCounts = new ConcurrentHashMap<>();
        resetCounters();
    }

    /**
     * Checks if a request is allowed based on the current limits.
     *
     * @return true if request is allowed, false otherwise
     */
    public synchronized boolean allowRequest() {
        Instant now = Instant.now();
        updateCountersIfNeeded(now);

        return incrementAndCheck("RPS", rpsLimit, now.getEpochSecond()) &&
                incrementAndCheck("RPM", rpmLimit, now.getEpochSecond() / 60) &&
                incrementAndCheck("RPH", rphLimit, now.getEpochSecond() / 3600) &&
                incrementAndCheck("RPD", rpdLimit, now.getEpochSecond() / 86400);
    }

    /**
     * Resets the request counters.
     */
    private void resetCounters() {
        requestCounts.clear();
        lastResetTime = Instant.now();
    }

    /**
     * Increments the request count for the given key and checks against the limit.
     */
    private boolean incrementAndCheck(String key, int limit, long timestamp) {
        String timeKey = key + "_" + timestamp;
        requestCounts.putIfAbsent(timeKey, new AtomicInteger(0));
        return requestCounts.get(timeKey).incrementAndGet() <= limit;
    }

    /**
     * Clears expired counters periodically.
     */
    private void updateCountersIfNeeded(Instant now) {
        if (now.isAfter(lastResetTime.plusSeconds(86400))) {
            resetCounters();
        }
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(5, 100, 5000, 100000);

        for (int i = 0; i < 10; i++) {
            if (rateLimiter.allowRequest()) {
                System.out.println("Request allowed");
            } else {
                System.out.println("Rate limit exceeded");
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

