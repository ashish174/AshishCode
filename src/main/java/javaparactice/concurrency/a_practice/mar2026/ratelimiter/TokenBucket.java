package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenBucket {
    private int bucketSize;
    private double refillRatePerSecond;
    private int currentTokens;
    private long lastRefillTimestamp;

    public TokenBucket(int bucketSize, double refillRatePerSecond) {
        this.bucketSize = bucketSize;
        this.refillRatePerSecond = refillRatePerSecond;
        this.currentTokens = bucketSize;
        this.lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean tryAquire(){
        long currTime = System.nanoTime();
        long nanoTimeElapsedSinceLastRefill = currTime - lastRefillTimestamp;
        if(nanoTimeElapsedSinceLastRefill > 0) {
            int tokenToRefill = (int) (refillRatePerSecond * (nanoTimeElapsedSinceLastRefill/(Math.pow(10, 9))));
            currentTokens = Math.min(bucketSize, currentTokens+tokenToRefill);
        }
        lastRefillTimestamp = currTime;
        if(currentTokens > 0){
            currentTokens--;
            return true;
        }
        return false;
    }
}
