package rateLimiter.V4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *  * Manages per-user rate limiting using three token-bucket windows:
 *  * requests-per-second (RPS), requests-per-minute (RPM), and requests-per-day (RPD).
 *  *
 *  * For each user we configure:
 *  *  - A per-second capacity (RPS)
 *  *  - A per-minute capacity (RPM)
 *  *  - A per-day capacity (RPD)
 *  *
 *  * For every incoming request, the manager checks the user's three buckets and
 *  * allows the request if at least one of the RPS, RPM, or RPD buckets still has
 *  * tokens available (i.e., the user has not exceeded that limit).
 *  *
 *  * Example:
 *  *   - userA: RPS=5, RPM=100, RPD=1000
 *  *   - userB: RPS=2, RPM=10,  RPD=50
 *  *
 *  *   Multiple rapid requests from userA will first consume the RPS bucket
 *  *   (up to 5 req/s), then fall back to RPM / RPD capacity as those refill
 *  *   over time, preventing long-term overuse while still allowing short bursts.
 *
 *
 *
 * Manager that holds rate-limiter buckets for multiple users.
 */
public class UserRateLimiterManager {

    // userId -> buckets
    private final Map<String, UserBuckets> userBucketsMap = new ConcurrentHashMap<>();

    /**
     * Register a user with RPS/RPM/RPD limits.
     */
    public void addUser(UserRateLimitConfig config) {
        userBucketsMap.put(config.userId, new UserBuckets(config));
    }

    /**
     * Check if a request for given user is allowed.
     * If user is unknown, you can either:
     *  - reject, OR
     *  - create default limits.
     */
    public boolean isAllowed(String userId) {
        UserBuckets buckets = userBucketsMap.get(userId);
        if (buckets == null) {
            // Unknown user: reject or initialize with default.
            return false;
        }
        return buckets.allowRequest();
    }

    // Simple demo
    public static void main(String[] args) throws InterruptedException {
        UserRateLimiterManager manager = new UserRateLimiterManager();

        // Example users: (rps, rpm, rpd)
        manager.addUser(new UserRateLimitConfig("userA", 5, 100, 1000));
        manager.addUser(new UserRateLimitConfig("userB", 2, 10, 50));

        System.out.println("=== Rapid requests for userA ===");
        for (int i = 1; i <= 10; i++) {
            boolean allowed = manager.isAllowed("userA");
            System.out.printf("userA request %d: %s%n", i, allowed ? "ALLOWED" : "DENIED");
            Thread.sleep(100); // 100ms between requests
        }

        System.out.println("\n=== Rapid requests for userB ===");
        for (int i = 1; i <= 100; i++) {
            boolean allowed = manager.isAllowed("userB");
            System.out.printf("userB request %d: %s%n", i, allowed ? "ALLOWED" : "DENIED");
            Thread.sleep(100); // 100ms between requests
        }
    }
}
