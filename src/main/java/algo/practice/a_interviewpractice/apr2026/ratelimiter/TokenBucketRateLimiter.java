package algo.practice.a_interviewpractice.apr2026.ratelimiter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter implements RateLimiter{
    private final long bucketCapacity;
    private final double refillRatePerNanoSec;
    private long availableTokens;
    private long lastUpdatedTimeInNano;
    private final Lock lock = new ReentrantLock();

    public TokenBucketRateLimiter(long capacity, double refillRatePerNanoSec){
        if(capacity<=0 || refillRatePerNanoSec <=0){
            throw new IllegalArgumentException("bucketSize and refillRatePerNanoSec must be positive");
        }
        bucketCapacity = capacity;
        this.refillRatePerNanoSec = refillRatePerNanoSec;
        availableTokens = capacity;
        lastUpdatedTimeInNano = System.nanoTime();
    }


    @Override
    public boolean tryAllow(long token) {
        if(token<=0){
            throw new IllegalArgumentException("token must be positive");
        }
        lock.lock();
        try{
            doRefill();
            if(availableTokens < token){
                System.out.printf("Token not available: Required: %d, Available: %d \n", token, availableTokens);
                return false;
            }
            System.out.printf("Token available: Required: %d, Available: %d \n", token, availableTokens);
            availableTokens-=token;
            return true;
        }finally{
            lock.unlock();
        }
    }

    @Override
    public boolean tryAllow() {
        return tryAllow(1l);
    }

    @Override
    public long availableToken() {
        lock.lock();
        try{
            doRefill();
            return availableTokens;
        }finally{
            lock.unlock();
        }
    }

    @Override
    public long nanoTimeTillTokenAvailable(long token) {
        if(token<=0){
            throw new IllegalArgumentException("token must be positive");
        }
        if(token > bucketCapacity){
            throw new IllegalArgumentException("token is > than bucketCapacity");
        }
        lock.lock();
        try{
            doRefill();
            if(availableTokens >= token){
                return 0L;
            } else{
                long extraTokenNeeded = token - availableTokens;
                long nanoTimeRequired = (long) Math.ceil(extraTokenNeeded/refillRatePerNanoSec);
                return nanoTimeRequired;
            }
        }finally{
            lock.unlock();
        }
    }

    @Override
    public void refill() {
        lock.lock();
        try{
            doRefill();
        } finally{
            lock.unlock();
        }
    }

    private void doRefill(){
        long timeNow = System.nanoTime();
        if(lastUpdatedTimeInNano>=timeNow){
            return;
        }
        long timeElapsed = timeNow - lastUpdatedTimeInNano;
        long tokenSinceLastTime = (long) Math.floor(refillRatePerNanoSec * timeElapsed);
        availableTokens = Math.min(bucketCapacity, availableTokens +tokenSinceLastTime);
        lastUpdatedTimeInNano = timeNow;
    }

    public static void main(String[] args){
        //5 rps
        TokenBucketRateLimiter tokenbucketRL = new TokenBucketRateLimiter(2, 5.0/1_000_000_000);
        Runnable runnable = () -> {
            for(int i=0; i < 50; i++){
                tokenbucketRL.tryAllow();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };


        Thread client1 = new Thread(runnable, "Thread1");
        Thread client2 = new Thread(runnable, "Thread2");
        Thread client3 = new Thread(runnable, "Thread3");

        client1.start();
        client2.start();
        client3.start();

    }
}
