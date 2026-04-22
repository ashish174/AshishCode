package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.RateLimitConfig;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.WindowRateLimitConfig;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.type.FixedWindowRateLimiter;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.type.SlidingWindowRateLimiter;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.type.TokenBucketRateLimiter;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RateLimiterDemo {
    private static final Logger LOGGER = Logger.getLogger(RateLimiterDemo.class.getName());

    private RateLimiterDemo() {
    }

    public static void main(String[] args) {
        Random random = new Random();

        runDemo("token-bucket", new TokenBucketRateLimiter(new RateLimitConfig(2, 0.5d)), random);
        runDemo("fixed-window", new FixedWindowRateLimiter(new WindowRateLimitConfig(3, Duration.ofSeconds(2))), random);
        runDemo("sliding-window", new SlidingWindowRateLimiter(new WindowRateLimitConfig(3, Duration.ofSeconds(2))), random);
    }

    private static void runDemo(String name, RateLimiter limiter, Random random) {
        LOGGER.info("---- " + name + " ----");

        for (int i = 0; i < 10; i++) {
            boolean allowed = limiter.tryAcquire();
            LOGGER.info(String.format(
                    "limiter=%s, request=%d, allowed=%s, availableTokens=%.3f, waitNanosForNext=%d",
                    name,
                    i,
                    allowed,
                    limiter.getAvailableTokens(),
                    limiter.nanosUntilAvailable(1)
            ));

            try {
                Thread.sleep(random.nextInt(2) * 500L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.log(Level.WARNING, "Demo interrupted", e);
                return;
            }
        }
    }
}
