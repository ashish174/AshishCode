package rateLimiter.V5;

/**
 * Simulates an API Gateway that enforces rate limits before forwarding requests.
 *
 * Design: Facade Pattern — single entry point hiding the registry + limiter internals.
 * Callers (controllers, filters) call gateway.handle() and get a result; they
 * don't need to know about tokens, buckets, or configs.
 *
 * In production this would integrate with a servlet Filter, gRPC interceptor,
 * or Spring HandlerInterceptor.
 */
public final class ApiGateway {

    private final RateLimitRegistry registry;

    public ApiGateway(RateLimitRegistry registry) {
        if (registry == null) throw new IllegalArgumentException("Registry must not be null");
        this.registry = registry;
    }

    /**
     * Handle an incoming API request.
     *
     * @param userId  The authenticated caller identity
     * @param apiPath The target endpoint (e.g. "/payments", "/search")
     * @return RateLimitResult — inspect isAllowed() to decide whether to proceed
     */
    public RateLimitResult handle(String userId, String apiPath) {
        RateLimitResult result = registry.check(userId, apiPath);
        logResult(result, userId, apiPath);
        return result;
    }

    private void logResult(RateLimitResult result, String userId, String apiPath) {
        if (result.isAllowed()) {
            System.out.printf("  %-10s → %-15s | ✅ ALLOWED  | remaining=%.1f%n",
                    userId, apiPath, result.getRemainingTokens());
        } else {
            System.out.printf("  %-10s → %-15s | ❌ DENIED   | limited by: %s%n",
                    userId, apiPath, result.getLimitedBy());
        }
    }
}
