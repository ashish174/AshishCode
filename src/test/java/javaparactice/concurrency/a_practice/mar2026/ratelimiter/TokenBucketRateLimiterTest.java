package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

import javaparactice.concurrency.a_practice.mar2026.ratelimiter.config.RateLimitConfig;
import javaparactice.concurrency.a_practice.mar2026.ratelimiter.type.TokenBucketRateLimiter;
import org.junit.Assert;
import org.junit.Test;

public class TokenBucketRateLimiterTest {

    @Test
    public void shouldAllowInitialBurstUpToCapacity() {
        FakeNanoClock clock = new FakeNanoClock();
        TokenBucketRateLimiter limiter =
                new TokenBucketRateLimiter(new RateLimitConfig(2, 1.0d), clock);

        Assert.assertTrue(limiter.tryAcquire());
        Assert.assertTrue(limiter.tryAcquire());
        Assert.assertFalse(limiter.tryAcquire());
    }

    @Test
    public void shouldRefillFractionallyOverTime() {
        FakeNanoClock clock = new FakeNanoClock();
        TokenBucketRateLimiter limiter =
                new TokenBucketRateLimiter(new RateLimitConfig(2, 0.5d), clock);

        Assert.assertTrue(limiter.tryAcquire());
        Assert.assertTrue(limiter.tryAcquire());
        Assert.assertFalse(limiter.tryAcquire());

        clock.advanceNanos(1_000_000_000L);
        Assert.assertFalse(limiter.tryAcquire());

        clock.advanceNanos(1_000_000_000L);
        Assert.assertTrue(limiter.tryAcquire());
    }

    @Test
    public void shouldCapRefillAtCapacity() {
        FakeNanoClock clock = new FakeNanoClock();
        TokenBucketRateLimiter limiter =
                new TokenBucketRateLimiter(new RateLimitConfig(3, 100.0d), clock);

        Assert.assertTrue(limiter.tryAcquire(3));
        clock.advanceNanos(10_000_000_000L);

        Assert.assertEquals(3.0d, limiter.getAvailableTokens(), 0.000001d);
    }

    @Test
    public void shouldReturnExpectedWaitTimeForMissingPermits() {
        FakeNanoClock clock = new FakeNanoClock();
        TokenBucketRateLimiter limiter =
                new TokenBucketRateLimiter(new RateLimitConfig(1, 2.0d), clock);

        Assert.assertTrue(limiter.tryAcquire());
        Assert.assertEquals(500_000_000L, limiter.nanosUntilAvailable(1));
    }

    private static final class FakeNanoClock implements NanoClock {
        private long now;

        @Override
        public long nanoTime() {
            return now;
        }

        void advanceNanos(long nanos) {
            now += nanos;
        }
    }
}
