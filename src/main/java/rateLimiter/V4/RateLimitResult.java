package rateLimiter.V4;

/**
 * Immutable result of a rate limit check.
 *
 * Design: Result Object pattern — richer than a boolean.
 * Carries metadata (tokens remaining, retry hint) for HTTP headers
 * like X-RateLimit-Remaining and Retry-After.
 */
public final class RateLimitResult {

    private final boolean allowed;
    private final double remainingTokens;
    private final String limitedBy;        // e.g. "user:alice" or "api:/payments"
    private final String policyName;
    private final long checkedAtNanos;

    private RateLimitResult(boolean allowed, double remainingTokens,
                            String limitedBy, String policyName) {
        this.allowed = allowed;
        this.remainingTokens = remainingTokens;
        this.limitedBy = limitedBy;
        this.policyName = policyName;
        this.checkedAtNanos = System.nanoTime();
    }

    public static RateLimitResult allowed(double remaining, String limitedBy, String policy) {
        return new RateLimitResult(true, remaining, limitedBy, policy);
    }

    public static RateLimitResult denied(String limitedBy, String policy) {
        return new RateLimitResult(false, 0, limitedBy, policy);
    }

    public boolean isAllowed()          { return allowed; }
    public boolean isDenied()           { return !allowed; }
    public double getRemainingTokens()  { return remainingTokens; }
    public String getLimitedBy()        { return limitedBy; }
    public String getPolicyName()       { return policyName; }

    @Override
    public String toString() {
        if (allowed) {
            return String.format("[ALLOWED] %s | policy=%s | remaining=%.1f tokens",
                    limitedBy, policyName, remainingTokens);
        } else {
            return String.format("[DENIED]  %s | policy=%s | rate limit exceeded",
                    limitedBy, policyName);
        }
    }
}
