package rateLimiter.V5;

/**
 * End-to-end demo showing the full rate limiting system.
 *
 * Scenarios covered:
 *  1. Standard per-user tier limits (free vs pro vs enterprise)
 *  2. Per-API endpoint limits (e.g. /payments stricter than /search)
 *  3. Dual-key limiting: both user AND api must have tokens
 *  4. Burst absorption and token refill
 *  5. Unknown users get default (free tier) automatically
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        // ── 1. Configure the registry ──────────────────────────────────────

        RateLimitRegistry registry = new RateLimitRegistry(
                RateLimitConfig.FREE_TIER,       // default for unknown users
                RateLimitConfig.PRO_TIER         // default for unknown APIs
        );

        // Register users with their tiers
        registry.registerUser("alice",            RateLimitConfig.FREE_TIER);
        registry.registerUser("bob",              RateLimitConfig.PRO_TIER);
        registry.registerUser("corp-service",     RateLimitConfig.ENTERPRISE_TIER);

        // Register API endpoints with their own limits
        // /payments is strict (sensitive endpoint)
        registry.registerApi("/payments", RateLimitConfig.builder("PAYMENTS_API")
                .capacity(5).refillRate(1.0).build());

        // /search is generous
        registry.registerApi("/search", RateLimitConfig.builder("SEARCH_API")
                .capacity(50).refillRate(20.0).build());

        // /health is essentially unlimited
        registry.registerApi("/health", RateLimitConfig.INTERNAL_SERVICE);

        ApiGateway gateway = new ApiGateway(registry);

        // ── 2. Scenario: Alice (free tier) hammers /search ──────────────────

        banner("Scenario 1: Alice (FREE_TIER, 10 tokens) sends 13 requests to /search");
        for (int i = 1; i <= 13; i++) {
            gateway.handle("alice", "/search");
        }

        // ── 3. Scenario: Bob (pro tier) uses /payments ───────────────────────

        banner("Scenario 2: Bob (PRO_TIER) sends 7 requests to /payments (limit=5)");
        for (int i = 1; i <= 7; i++) {
            gateway.handle("bob", "/payments");
        }

        // ── 4. Scenario: Dual-key limiting ───────────────────────────────────

        banner("Scenario 3: corp-service hits /payments — API limit kicks in even though user has tokens");
        // /payments has 5 tokens (already consumed 7 above — now at 0 + some refill)
        // Let the /payments bucket refill for 3 seconds (3 tokens)
        System.out.println("  [waiting 3s for /payments to refill ~3 tokens...]");
        Thread.sleep(3000);
        for (int i = 1; i <= 5; i++) {
            gateway.handle("corp-service", "/payments");
        }

        // ── 5. Scenario: Unknown user gets default (free) tier ───────────────

        banner("Scenario 4: Unknown user 'zara' gets default FREE_TIER automatically");
        for (int i = 1; i <= 4; i++) {
            gateway.handle("zara", "/search");
        }

        // ── 6. Scenario: Token refill ────────────────────────────────────────

        banner("Scenario 5: Alice waits 5s (refill=1/s → 5 new tokens), then retries");
        System.out.println("  [waiting 5s for alice's token bucket to refill...]");
        Thread.sleep(5000);
        for (int i = 1; i <= 6; i++) {
            gateway.handle("alice", "/search");
        }

        // ── 7. Final registry state ───────────────────────────────────────────

        registry.printSummary();
    }

    private static void banner(String title) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.printf( "║  %-52s  ║%n", title);
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
