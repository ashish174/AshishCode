package javaparactice.concurrency.a_practice.mar2026.ratelimiter;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class RateLimiter {
    public static void main(String[] args){
        TokenBucket tokenBucket = new TokenBucket(2, 0.5);
        Random random = new Random();
        int i = 0;
        while(i < 100){
            if(tokenBucket.tryAquire()){
                log.info("Token Acquired, timeStampInSec : {}", System.nanoTime());
            } else {
                log.info("Token exhausted, timeStampInSec : {}", System.nanoTime());
            }
            try{
                Thread.sleep( random.nextInt(2) * 1000);
            } catch (InterruptedException e) {
                log.warn("Thread Interrupted");
                Thread.currentThread().interrupt();
                return;
            }
            i++;

        }
    }
}
