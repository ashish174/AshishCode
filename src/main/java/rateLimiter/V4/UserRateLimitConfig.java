package rateLimiter.V4;

/**
 * Per-user configuration of limits:
 * - rps: requests per second
 * - rpm: requests per minute
 * - rpd: requests per day
 */
class UserRateLimitConfig {
    final String userId;
    final long rpsCapacity;
    final long rpmCapacity;
    final long rpdCapacity;

    public UserRateLimitConfig(String userId, long rpsCapacity, long rpmCapacity, long rpdCapacity) {
        this.userId = userId;
        this.rpsCapacity = rpsCapacity;
        this.rpmCapacity = rpmCapacity;
        this.rpdCapacity = rpdCapacity;
    }
}