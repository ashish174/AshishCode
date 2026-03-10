package rateLimiter.V5;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Central registry that owns all active {@link RateLimiter} instances.
 *
 * Design:
 *  - Registry Pattern: single source of truth for limiter lookup.
 *  - Separation of Concerns: registry manages lifecycle; limiters manage tokens.
 *  - Thread-safe: ConcurrentHashMap + computeIfAbsent for lock-free lazy creation.
 *
 * Supports two independent limit dimensions:
 *  1. Per-User  — "user:alice"  limits total requests from a user
 *  2. Per-API   — "api:/payments" limits total requests to an endpoint
 *
 * In a distributed system this would be backed by Redis;
 * here it is in-process for the interview scope.
 */
public final class RateLimitRegistry {

    // key → RateLimiter  (e.g. "user:alice" → TokenBucketRateLimiter)
    private final ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    // Default configs applied when a key is first seen
    private final Map<String, RateLimitConfig> userConfigs = new ConcurrentHashMap<>();
    private final Map<String, RateLimitConfig> apiConfigs  = new ConcurrentHashMap<>();

    private final RateLimitConfig defaultUserConfig;
    private final RateLimitConfig defaultApiConfig;

    // ── Construction ───────────────────────────────────────────────────────

    public RateLimitRegistry(RateLimitConfig defaultUserConfig,
                             RateLimitConfig defaultApiConfig) {
        this.defaultUserConfig = defaultUserConfig;
        this.defaultApiConfig  = defaultApiConfig;
    }

    // ── Registration ──────────────────────────────────────────────────────

    /** Pre-register a specific config for a user. */
    public void registerUser(String userId, RateLimitConfig config) {
        validate(userId, config);
        userConfigs.put(userId, config);
    }

    /** Pre-register a specific config for an API endpoint. */
    public void registerApi(String apiPath, RateLimitConfig config) {
        validate(apiPath, config);
        apiConfigs.put(apiPath, config);
    }

    // ── Core rate-limit check ──────────────────────────────────────────────

    /**
     * Check rate limit for a (userId, apiPath) pair.
     *
     * Both the user AND the API endpoint must have available tokens.
     * This mirrors real-world dual-key limiting (per-client AND per-route).
     *
     * @return RateLimitResult describing the outcome
     */
    public RateLimitResult check(String userId, String apiPath) {
        RateLimiter userLimiter = getLimiterForUser(userId);
        RateLimiter apiLimiter  = getLimiterForApi(apiPath);

        // Check user limit first
        if (!userLimiter.tryAcquire()) {
            return RateLimitResult.denied("user:" + userId, userLimiter.getConfig().getPolicyName());
        }

        // Then check API limit
        if (!apiLimiter.tryAcquire()) {
            return RateLimitResult.denied("api:" + apiPath, apiLimiter.getConfig().getPolicyName());
        }

        double remaining = Math.min(
                userLimiter.getAvailableTokens(),
                apiLimiter.getAvailableTokens()
        );
        return RateLimitResult.allowed(remaining, "user:" + userId + " → api:" + apiPath,
                userLimiter.getConfig().getPolicyName());
    }

    // ── Lazy limiter resolution ─────────────────────────────────────────────

    private RateLimiter getLimiterForUser(String userId) {
        return limiters.computeIfAbsent("user:" + userId, key -> {
            RateLimitConfig cfg = userConfigs.getOrDefault(userId, defaultUserConfig);
            return RateLimiterFactory.tokenBucket(cfg);
        });
    }

    private RateLimiter getLimiterForApi(String apiPath) {
        return limiters.computeIfAbsent("api:" + apiPath, key -> {
            RateLimitConfig cfg = apiConfigs.getOrDefault(apiPath, defaultApiConfig);
            return RateLimiterFactory.tokenBucket(cfg);
        });
    }

    // ── Observability ───────────────────────────────────────────────────────

    public Optional<RateLimiter> getLimiter(String key) {
        return Optional.ofNullable(limiters.get(key));
    }

    public int getActiveLimiterCount() {
        return limiters.size();
    }

    public void printSummary() {
        System.out.println("\n── Registry Summary ─────────────────────────────");
        limiters.forEach((key, limiter) ->
                System.out.printf("  %-35s | tokens=%.1f | %s%n",
                        key, limiter.getAvailableTokens(), limiter.getConfig().getPolicyName()));
        System.out.println("─────────────────────────────────────────────────");
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private void validate(String key, RateLimitConfig config) {
        if (key == null || key.isBlank()) throw new IllegalArgumentException("Key must not be blank");
        if (config == null) throw new IllegalArgumentException("Config must not be null");
    }
}
