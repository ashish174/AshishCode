package algo.practice.a_interviewpractice.apr2026.ratelimiter;

public interface RateLimiter {
    boolean tryAllow(long token);
    boolean tryAllow();

    long availableToken();

    long nanoTimeTillTokenAvailable(long token);

    void refill();


}
