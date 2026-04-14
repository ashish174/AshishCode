package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RateLimiterDemo {
    private static final Logger LOGGER = Logger.getLogger(RateLimiterDemo.class.getName());

    private RateLimiterDemo() {
    }

    public static void main(String[] args) {
        RateLimiter limiter = new TokenBucketRateLimiter(new RateLimitConfig(2, 0.5d));
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            boolean allowed = limiter.tryAcquire();
            LOGGER.info(String.format(
                    "request=%d, allowed=%s, availableTokens=%.3f, waitNanosForNext=%d",
                    i,
                    allowed,
                    limiter.getAvailableTokens(),
                    limiter.nanosUntilAvailable(1)
            ));

            try {
                Thread.sleep(random.nextInt(2) * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.log(Level.WARNING, "Demo interrupted", e);
                return;
            }
        }
    }
}
