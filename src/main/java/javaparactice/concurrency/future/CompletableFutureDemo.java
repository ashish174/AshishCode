package javaparactice.concurrency.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    private static Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletableFuture<Integer> completableFuture = CompletableFuture
            .supplyAsync(() -> Factorial.findFactorial(35), threadPool);
        while(!completableFuture.isDone()){
            logger.info("Task Not Completed.");
        }
        logger.info("Factorial : {}", completableFuture.get());


    }
}
