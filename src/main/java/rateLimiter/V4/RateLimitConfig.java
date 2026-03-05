package rateLimiter.V4;

/**
 * Immutable value object representing a rate limit policy.
 *
 * Design: Value Object pattern — no identity, equality by value.
 * All fields are validated at construction time (fail-fast).
 */
public final class RateLimitConfig {

    private final long capacity;       // Max burst size (tokens)
    private final double refillRate;   // Tokens added per second
    private final String policyName;   // Human-readable label e.g. "FREE_TIER"

    private RateLimitConfig(Builder builder) {
        this.capacity = builder.capacity;
        this.refillRate = builder.refillRate;
        this.policyName = builder.policyName;
    }

    public long getCapacity()      { return capacity; }
    public double getRefillRate()  { return refillRate; }
    public String getPolicyName()  { return policyName; }

    @Override
    public String toString() {
        return String.format("RateLimitConfig{policy='%s', capacity=%d, refillRate=%.1f/s}",
                policyName, capacity, refillRate);
    }

    // ── Builder ──────────────────────────────────────────────

    public static Builder builder(String policyName) {
        return new Builder(policyName);
    }

    public static final class Builder {
        private final String policyName;
        private long capacity;
        private double refillRate;

        private Builder(String policyName) {
            if (policyName == null || policyName.isBlank())
                throw new IllegalArgumentException("Policy name must not be blank");
            this.policyName = policyName;
        }

        public Builder capacity(long capacity) {
            if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
            this.capacity = capacity;
            return this;
        }

        public Builder refillRate(double refillRate) {
            if (refillRate <= 0) throw new IllegalArgumentException("Refill rate must be > 0");
            this.refillRate = refillRate;
            return this;
        }

        public RateLimitConfig build() {
            if (capacity == 0) throw new IllegalStateException("Capacity not set");
            if (refillRate == 0) throw new IllegalStateException("Refill rate not set");
            return new RateLimitConfig(this);
        }
    }

    // ── Pre-built standard tiers (Open/Closed Principle: extend by adding constants) ──

    public static final RateLimitConfig FREE_TIER = RateLimitConfig.builder("FREE_TIER")
            .capacity(10).refillRate(1.0).build();

    public static final RateLimitConfig PRO_TIER = RateLimitConfig.builder("PRO_TIER")
            .capacity(100).refillRate(10.0).build();

    public static final RateLimitConfig ENTERPRISE_TIER = RateLimitConfig.builder("ENTERPRISE_TIER")
            .capacity(1000).refillRate(100.0).build();

    public static final RateLimitConfig INTERNAL_SERVICE = RateLimitConfig.builder("INTERNAL_SERVICE")
            .capacity(10000).refillRate(1000.0).build();
}
