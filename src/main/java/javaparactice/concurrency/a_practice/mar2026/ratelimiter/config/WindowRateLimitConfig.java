package javaparactice.concurrency.a_practice.mar2026.ratelimiter.config;

import java.time.Duration;

/**
 * Immutable configuration for fixed-window and sliding-window limiters.
 *
 * maxRequests: maximum admitted requests in one logical window
 * window: size of the rolling or fixed window
 */
public record WindowRateLimitConfig(int maxRequests, Duration window) {

    public WindowRateLimitConfig {
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("maxRequests must be greater than 0");
        }
        if (window == null || window.isNegative() || window.isZero()) {
            throw new IllegalArgumentException("window must be greater than 0");
        }
    }

    public long windowNanos() {
        return window.toNanos();
    }
}
