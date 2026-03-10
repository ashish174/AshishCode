package rateLimiter.V5;

/**
 * Factory for creating {@link RateLimiter} instances.
 *
 * Design: Factory Method Pattern — decouples creation from usage.
 * To add a new algorithm (e.g. SlidingWindowRateLimiter), add a new
 * factory method here without touching any existing code (OCP).
 *
 * Also acts as a central extension point: add metrics, decorators,
 * or logging here without modifying the concrete limiter classes.
 */
public final class RateLimiterFactory {

    private RateLimiterFactory() {} // utility class

    /** Create a Token Bucket limiter from a config. */
    public static RateLimiter tokenBucket(RateLimitConfig config) {
        return new TokenBucketRateLimiter(config);
    }

    /** Convenience: create a Token Bucket limiter from named tier constants. */
    public static RateLimiter forFreeTier()       { return tokenBucket(RateLimitConfig.FREE_TIER); }
    public static RateLimiter forProTier()        { return tokenBucket(RateLimitConfig.PRO_TIER); }
    public static RateLimiter forEnterpriseTier() { return tokenBucket(RateLimitConfig.ENTERPRISE_TIER); }
    public static RateLimiter forInternalService(){ return tokenBucket(RateLimitConfig.INTERNAL_SERVICE); }

    // Future extension points (OCP — no modification needed above):
    // public static RateLimiter slidingWindow(RateLimitConfig config) { ... }
    // public static RateLimiter leakyBucket(RateLimitConfig config) { ... }
}
