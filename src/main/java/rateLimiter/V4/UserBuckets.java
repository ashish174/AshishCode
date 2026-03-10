package rateLimiter.V4;

/**
 * Holds the 3 buckets for a given user:
 * - rpsBucket: per-second
 * - rpmBucket: per-minute
 * - rpdBucket: per-day
 */
class UserBuckets {
    final TokenBucketRateLimiter rpsBucket;
    final TokenBucketRateLimiter rpmBucket;
    final TokenBucketRateLimiter rpdBucket;

    public UserBuckets(UserRateLimitConfig config) {
        // refill rates:
        //   RPS: capacity tokens per second
        //   RPM: capacity tokens per 60 seconds
        //   RPD: capacity tokens per 86400 seconds (24h)
        this.rpsBucket = new TokenBucketRateLimiter(config.rpsCapacity, config.rpsCapacity);
        this.rpmBucket = new TokenBucketRateLimiter(config.rpmCapacity, config.rpmCapacity / 60.0);
        this.rpdBucket = new TokenBucketRateLimiter(config.rpdCapacity, config.rpdCapacity / 86_400.0);
    }

    /**
     * Allow if user has tokens left in ANY of the three buckets.
     * Change OR to AND if you want stricter behavior.
     */
    public boolean allowRequest() {
        // Example policy:
        //   If user still has per-second tokens OR per-minute tokens OR per-day tokens,
        //   we allow, and consume from the first bucket that has capacity.
        // You can change the order or logic as needed.
        if (rpsBucket.tryAcquire()) {
            return true;
        }
        if (rpmBucket.tryAcquire()) {
            return true;
        }
        return rpdBucket.tryAcquire();
    }
}
